package com.inflames.curenciesviewmodel.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cryptoList: List<CryptoDatabaseModel>)

    @Query("SELECT * FROM crypto_database")
    fun getCryptos(): LiveData<List<CryptoDatabaseModel>>

}

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


