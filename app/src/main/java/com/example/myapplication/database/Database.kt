package com.example.myapplication.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.model.CoinPriceInfo
import com.example.myapplication.database.dao.CoinPriceInfoDao
import com.example.myapplication.utils.Constants.DB_NAME

@androidx.room.Database(entities = [CoinPriceInfo::class], version = 2, exportSchema = false)
abstract class Database : RoomDatabase() {

    companion object {
        private var db: Database? = null
        fun getInstance(context: Context): Database {
            synchronized(this) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    Database::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
                db = instance
                return instance
            }
        }
    }
    abstract fun coinPriceInfoDao(): CoinPriceInfoDao
}