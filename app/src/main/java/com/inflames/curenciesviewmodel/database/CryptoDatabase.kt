package com.inflames.curenciesviewmodel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.inflames.curenciesviewmodel.model.CryptoDetailDatabaseModel
import com.inflames.curenciesviewmodel.model.CryptoDetailModel

@Database(entities = [CryptoDatabaseModel::class,CryptoDetailDatabaseModel::class], version = 6, exportSchema = false)
abstract class CryptoDatabase : RoomDatabase() {

    abstract val cryptoDao: CryptoDao


    companion object {

        @Volatile
        private var INSTANCE: CryptoDatabase? = null

        fun getDatabase(context: Context): CryptoDatabase {

            var instance = INSTANCE
            synchronized(this) {
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(context, CryptoDatabase::class.java, "Crypto_database")
                            .fallbackToDestructiveMigration()
                            .build()

                    INSTANCE = instance
                }
            }

            return instance!!
        }
    }

}