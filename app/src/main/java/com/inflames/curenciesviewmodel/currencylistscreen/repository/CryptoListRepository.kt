package com.inflames.curenciesviewmodel.currencylistscreen.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.inflames.curenciesviewmodel.database.CryptoDatabase
import com.inflames.curenciesviewmodel.database.asDomainModel
import com.inflames.curenciesviewmodel.model.CryptoModel
import com.inflames.curenciesviewmodel.model.asDatabaseModel
import com.inflames.curenciesviewmodel.network.CryptoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoListRepository(val database: CryptoDatabase) {

    val cryptos: LiveData<List<CryptoModel>> =
        Transformations.map(database.cryptoDao.getCryptos()) {
            it.asDomainModel()
        }



    suspend fun refreshCrypto() {
        withContext(Dispatchers.IO) {
            val cryptoList = CryptoService.cryptoService.getCryptoList()
            database.cryptoDao.insertAll(cryptoList.asDatabaseModel())
        }

    }




}