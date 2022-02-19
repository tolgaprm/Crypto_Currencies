package com.inflames.curenciesviewmodel.currencylistscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inflames.curenciesviewmodel.currencylistscreen.repository.CryptoListRepository
import com.inflames.curenciesviewmodel.database.CryptoDatabase
import com.inflames.curenciesviewmodel.enums.CryptoApiStatus
import kotlinx.coroutines.launch
import timber.log.Timber


class CryptoListViewModel(application: Application) : AndroidViewModel(application) {


    private val _status = MutableLiveData<CryptoApiStatus>()
    val status: LiveData<CryptoApiStatus> get() = _status

    private val cryptoListRepository =
        CryptoListRepository(CryptoDatabase.getDatabase(application.applicationContext))
    var list = cryptoListRepository.cryptos

    init {
        refreshDataFromRepository()
    }


    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            _status.value = CryptoApiStatus.LOADING
            try {
                cryptoListRepository.refreshCrypto()
                _status.value = CryptoApiStatus.DONE

            } catch (e: Exception) {
                if (list.value.isNullOrEmpty()) {
                    Timber.e("Caught exception while it is getting from internet, Exception:  " + e.localizedMessage)
                    _status.value = CryptoApiStatus.ERROR
                } else {
                    _status.value = CryptoApiStatus.OTHER
                }


            }
        }
    }

    fun updateCryptoList() {
        refreshDataFromRepository()
    }


}