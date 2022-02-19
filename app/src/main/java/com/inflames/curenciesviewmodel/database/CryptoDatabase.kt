package com.inflames.curenciesviewmodel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CryptoDatabaseModel::class], version = 1)
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
                            .build()

                    INSTANCE = instance
                }
            }

            return instance!!
        }
    }

}