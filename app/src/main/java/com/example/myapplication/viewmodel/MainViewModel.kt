package com.example.myapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.api.ApiFactory
import com.example.myapplication.database.Database
import com.example.myapplication.model.CoinPriceInfo
import com.example.myapplication.model.CoinPriceInfoRawData
import com.google.gson.Gson
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private val database = Database.getInstance(application).coinPriceInfoDao()
    val priceList = database.getPriceList()

    init {
        loadData()
    }

    fun getDetailInfo(fSymbol : String) : LiveData<CoinPriceInfo> {
        return database.getPriceInfoAboutCoin(fSymbol)
    }

    private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo()
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",").toString()}
            .flatMap { ApiFactory.apiService.getFullPriceList(fSymbols = it) }
            .map { getPriceListFromRawData(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                       database.insertPriceList(it)
            }, {
                Log.d("TEST_OF_LOADING_DATA", "Failed : ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData : CoinPriceInfoRawData
    ) : List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}