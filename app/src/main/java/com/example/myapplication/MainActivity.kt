package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.adapter.CoinInfoAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.CoinPriceInfo
import com.example.myapplication.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        val adapter = CoinInfoAdapter(this)

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun oncCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(this@MainActivity, coinPriceInfo.fromsymbol)
                startActivity(intent)
            }
        }

        binding.recyclerViewCoinPriceList.adapter = adapter
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.priceList.observe(this) {
            adapter.coinInfoList = it
        }
    }
}