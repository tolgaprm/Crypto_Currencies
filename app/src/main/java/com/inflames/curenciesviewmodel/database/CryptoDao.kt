package com.inflames.curenciesviewmodel.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cryptoList: List<CryptoDatabaseModel>)

    @Query("SELECT * FROM crypto_database")
    fun getCryptos(): LiveData<List<CryptoDatabaseModel>>

    @Query("SELECT * FROM crypto_database WHERE name LIKE :name ")
    fun getCryptoThatSearching(name: String): LiveData<List<CryptoDatabaseModel>>



}




