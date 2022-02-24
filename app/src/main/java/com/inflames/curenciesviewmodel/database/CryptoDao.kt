package com.inflames.curenciesviewmodel.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inflames.curenciesviewmodel.model.CryptoDetailDatabaseModel
import com.inflames.curenciesviewmodel.model.CryptoDetailModel


@Dao
interface CryptoDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = CryptoDatabaseModel::class)
    suspend fun insertAll(cryptoList: List<CryptoDatabaseModel>)

    //  Cryptos that we saved get from Database
    @Query("SELECT * FROM crypto_database")
    fun getCryptos(): LiveData<List<CryptoDatabaseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = CryptoDetailDatabaseModel::class)
    suspend fun insertAllCryptoDetails(cryptoDetails: List<CryptoDetailDatabaseModel>)

    @Query("SELECT * FROM cryptoDetail WHERE id =:aa")
    fun getCryptoDetailById(aa: String): LiveData<CryptoDetailDatabaseModel>


}




