package com.inflames.curenciesviewmodel.currencylistscreen.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.inflames.curenciesviewmodel.model.CryptoModel
import com.inflames.curenciesviewmodel.network.CryptoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoListRepository {

     val _cryptoList = MutableLiveData<List<CryptoModel>>(listOf())
    val cryptoList: LiveData<List<CryptoModel>>
        get() = _cryptoList


    // get cryptoLists From APi
    suspend fun getCryptoList() {
        withContext(Dispatchers.Default) {
            _cryptoList.postValue(CryptoService.cryptoService.getCryptoList())
        }

    }

}