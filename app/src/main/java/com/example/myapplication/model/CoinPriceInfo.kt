package com.example.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.utils.Constants.BASE_IMAGE_URL
import com.example.myapplication.utils.convertTimeStampToTime
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinPriceInfo(
    @Expose
    @SerializedName("TYPE")
    val type: String? = null,
    @Expose
    @SerializedName("MARKET")
    val market: String? = null,
    @Expose
    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    val fromsymbol: String,
    @Expose
    @SerializedName("TOSYMBOL")
    val tosymbol: String? = null,
    @Expose
    @SerializedName("LASTMARKET")
    val lastmarket: String? = null,
    @Expose
    @SerializedName("MEDIAN")
    val median: Double? = null,
    @Expose
    @SerializedName("TOPTIERVOLUME24HOUR")
    val toptiervolume24hour: Double? = null,
    @Expose
    @SerializedName("TOPTIERVOLUME24HOURTO")
    val toptiervolume24hourto: Double? = null,
    @Expose
    @SerializedName("LASTTRADEID")
    val lasttradeid: String? = null,
    @Expose
    @SerializedName("PRICE")
    val price: String? = null,
    @Expose
    @SerializedName("LASTUPDATE")
    val lastupdate: Long? = null,
    @Expose
    @SerializedName("LASTVOLUME")
    val lastvolume: Double? = null,
    @Expose
    @SerializedName("LASTVOLUMETO")
    val lastvolumeto: Double? = null,
    @Expose
    @SerializedName("VOLUMEHOUR")
    val volumehour: Double? = null,
    @Expose
    @SerializedName("VOLUMEHOURTO")
    val volumehourto: Double? = null,
    @Expose
    @SerializedName("OPENHOUR")
    val openhour: Double? = null,
    @Expose
    @SerializedName("HIGHHOUR")
    val highhour: Double? = null,
    @Expose
    @SerializedName("LOWHOUR")
    val lowhour: Double? = null,
    @Expose
    @SerializedName("VOLUMEDAY")
    val volumeday: Double? = null,
    @Expose
    @SerializedName("VOLUMEDAYTO")
    val volumedayto: Double? = null,
    @Expose
    @SerializedName("OPENDAY")
    val openday: Double? = null,
    @Expose
    @SerializedName("HIGHDAY")
    val highday: String? = null,
    @Expose
    @SerializedName("LOWDAY")
    val lowday: String? = null,
    @Expose
    @SerializedName("VOLUME24HOUR")
    val volume24hour: Double? = null,
    @Expose
    @SerializedName("VOLUME24HOURTO")
    val volume24hourto: Double? = null,
    @Expose
    @SerializedName("OPEN24HOUR")
    val open24hour: Double? = null,
    @Expose
    @SerializedName("HIGH24HOUR")
    val high24hour: Double? = null,
    @Expose
    @SerializedName("LOW24HOUR")
    val low24hour: Double? = null,
    @Expose
    @SerializedName("CHANGE24HOUR")
    val change24hour: Double? = null,
    @Expose
    @SerializedName("CHANGEPCT24HOUR")
    val changepct24hour: Double? = null,
    @Expose
    @SerializedName("CHANGEDAY")
    val changeday: Double? = null,
    @Expose
    @SerializedName("CHANGEPCTDAY")
    val changepctday: Double? = null,
    @Expose
    @SerializedName("CHANGEHOUR")
    val changehour: Double? = null,
    @Expose
    @SerializedName("CHANGEPCTHOUR")
    val changepcthour: Double? = null,
    @Expose
    @SerializedName("CONVERSIONTYPE")
    val conversiontype: String? = null,
    @Expose
    @SerializedName("CONVERSIONSYMBOL")
    val conversionsymbol: String? = null,
    @Expose
    @SerializedName("CONVERSIONLASTUPDATE")
    val conversionlastupdate: Int? = null,
    @Expose
    @SerializedName("SUPPLY")
    val supply: Long? = null,
    @Expose
    @SerializedName("MKTCAP")
    val mktcap: Double? = null,
    @Expose
    @SerializedName("MKTCAPPENALTY")
    val mktcappenalty: Int? = null,
    @Expose
    @SerializedName("CIRCULATINGSUPPLY")
    val circulatingsupply: Int? = null,
    @Expose
    @SerializedName("CIRCULATINGSUPPLYMKTCAP")
    val circulatingsupplymktcap: Double? = null,
    @Expose
    @SerializedName("TOTALVOLUME24H")
    val totalvolume24h: Double? = null,
    @Expose
    @SerializedName("TOTALVOLUME24HTO")
    val totalvolume24hto: Double? = null,
    @Expose
    @SerializedName("TOTALTOPTIERVOLUME24H")
    val totaltoptiervolume24h: Double? = null,
    @Expose
    @SerializedName("TOTALTOPTIERVOLUME24HTO")
    val totaltoptiervolume24hto: Double? = null,
    @Expose
    @SerializedName("IMAGEURL")
    val imageurl: String? = null,
) {
    fun getFormattedTime() : String = convertTimeStampToTime(lastupdate)
    fun getFullImageUrl() : String = BASE_IMAGE_URL + imageurl
}