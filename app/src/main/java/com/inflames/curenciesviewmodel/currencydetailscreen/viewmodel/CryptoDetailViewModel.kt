package com.inflames.curenciesviewmodel.currencydetailscreen.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.inflames.curenciesviewmodel.currencydetailscreen.repository.CryptoDetailRepository
import com.inflames.curenciesviewmodel.database.CryptoDatabase
import com.inflames.curenciesviewmodel.enums.CryptoApiStatus
import com.inflames.curenciesviewmodel.model.CryptoModel

class CryptoDetailViewModel(app: Application, private val clickedCryptoModelById: CryptoModel) :
    AndroidViewModel(app) {

    private val _selectedCryptoFromCryptoList = MutableLiveData<CryptoModel>()
    val selectedCryptoFromCryptoList: LiveData<CryptoModel> get() = _selectedCryptoFromCryptoList

    private val _status = MutableLiveData<CryptoApiStatus>()
    val status: LiveData<CryptoApiStatus> get() = _status

    val repository = CryptoDetailRepository(
        CryptoDatabase.getDatabase(app.applicationContext),
        clickedCryptoModelById.id
    )

    var cryptoDetail = repository.cryptoDetail

    init {
        _selectedCryptoFromCryptoList.value = clickedCryptoModelById
    }


}