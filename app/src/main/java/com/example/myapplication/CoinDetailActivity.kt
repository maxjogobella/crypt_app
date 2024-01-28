package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.utils.Constants.EXTRA_FROM_SYMBOL
import com.example.myapplication.databinding.ActivityCoinDetailBinding
import com.example.myapplication.viewmodel.MainViewModel
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel
    private lateinit var binding : ActivityCoinDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        fromSymbol?.let {
            viewModel.getDetailInfo(it).observe(this) {
                val template = getString(R.string.sym_template)
                with(binding) {
                    tvPriceCoinDetail.text = it.price
                    tvMinDayDetail.text = it.lowday
                    tvMaxDayDetail.text = it.highday
                    tvLastOfferDetail.text = String.format(getString(R.string.lastoffer), it.lastmarket)
                    tvLastUpdateDetail.text = String.format(getString(R.string.lastupdate), it.getFormattedTime())
                    tvSymbolsDetail.text = String.format(template, it.tosymbol, it.fromsymbol)
                    Picasso.get().load(it.getFullImageUrl()).into(ivLogoCoinsDetail)
                }
            }
        }
    }

    companion object {
        fun newIntent(context : Context, fromSymbol : String) : Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}