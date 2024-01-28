package com.example.myapplication.api

import com.example.myapplication.model.CoinInfoListOfData
import com.example.myapplication.model.CoinPriceInfoRawData
import com.example.myapplication.utils.Constants.API_KEY
import com.example.myapplication.utils.Constants.CURRENCY
import com.example.myapplication.utils.Constants.QUERY_PARAM_API_KEY
import com.example.myapplication.utils.Constants.QUERY_PARAM_FROM_SYMBOLS
import com.example.myapplication.utils.Constants.QUERY_PARAM_LIMIT
import com.example.myapplication.utils.Constants.QUERY_PARAM_TO_SYMBOLS
import com.example.myapplication.utils.Constants.QUERY_TO_SYMBOL
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey : String = API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit : Int = 10,
        @Query(QUERY_TO_SYMBOL) tSymbol : String = CURRENCY
    ) : Single<CoinInfoListOfData>

    @GET("pricemultifull?")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey : String = API_KEY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSymbols : String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSymbols : String = CURRENCY
    ) : Single<CoinPriceInfoRawData>
}