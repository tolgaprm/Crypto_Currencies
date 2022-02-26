package com.inflames.curenciesviewmodel.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inflames.curenciesviewmodel.database.entity.CryptoDatabaseModel
import com.inflames.curenciesviewmodel.database.entity.CryptoDetailDatabaseModel


@Dao
interface CryptoDao {


    // To save crypto data that got from API, on local database
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = CryptoDatabaseModel::class)
    suspend fun insertAll(cryptoList: List<CryptoDatabaseModel>)

    //  Cryptos that we saved get from Database
    @Query("SELECT * FROM crypto_database")
    fun getCryptos(): LiveData<List<CryptoDatabaseModel>>

    //  To save crypto details data that got from API, on local database
    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = CryptoDetailDatabaseModel::class)
    suspend fun insertAllCryptoDetails(cryptoDetails: List<CryptoDetailDatabaseModel>)


    // To get crypto detail data that selected crypto item
    @Query("SELECT * FROM cryptoDetail WHERE id =:cryptoId")
    fun getCryptoDetailById(cryptoId: String): LiveData<CryptoDetailDatabaseModel>


}




