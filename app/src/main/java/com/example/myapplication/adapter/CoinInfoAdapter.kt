package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemCoinInfoBinding
import com.example.myapplication.model.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context : Context) : Adapter<CoinInfoAdapter.CoinViewHolder>() {

    var onCoinClickListener : OnCoinClickListener? = null
    var coinInfoList : List<CoinPriceInfo> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding =
            ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
            val symbolsTemplate = context.resources.getString(R.string.sym_template)
            tvSymbols.text = String.format(symbolsTemplate, coin.fromsymbol, coin.tosymbol)
            tvPriceCoin.text = coin.price.toString()
            tvLastUpdate.text = coin.getFormattedTime()
            Picasso.get().load(coin.getFullImageUrl()).into(ivLogoCoins)

            itemView.setOnClickListener {
                onCoinClickListener?.oncCoinClick(coin)
            }
        }

    }

    override fun getItemCount() :Int = coinInfoList.size


    inner class CoinViewHolder(binding: ItemCoinInfoBinding) : ViewHolder(binding.root) {
        val ivLogoCoins = binding.ivLogoCoins
        val tvSymbols = binding.tvSymbols
        val tvPriceCoin = binding.tvPriceCoin
        val tvLastUpdate = binding.tvLastUpdate
    }

    interface OnCoinClickListener {
        fun oncCoinClick(coinPriceInfo : CoinPriceInfo)
    }
}