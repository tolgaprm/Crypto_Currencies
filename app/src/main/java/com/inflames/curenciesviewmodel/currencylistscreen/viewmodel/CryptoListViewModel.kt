package com.inflames.curenciesviewmodel.currencylistscreen.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inflames.curenciesviewmodel.currencylistscreen.repository.CryptoListRepository
import com.inflames.curenciesviewmodel.database.CryptoDatabase
import com.inflames.curenciesviewmodel.enums.CryptoApiStatus
import com.inflames.curenciesviewmodel.model.CryptoModel
import kotlinx.coroutines.launch
import timber.log.Timber


class CryptoListViewModel(application: Application) : AndroidViewModel(application) {


    private val _status = MutableLiveData<CryptoApiStatus>()
    val status: LiveData<CryptoApiStatus> get() = _status

    private val cryptoListRepository =
        CryptoListRepository(CryptoDatabase.getDatabase(application.applicationContext))
    var list = cryptoListRepository.cryptos

    private val _navigateToSelectedProperty = MutableLiveData<CryptoModel>()
    val navigateToSelectedProperty: LiveData<CryptoModel>
        get() = _navigateToSelectedProperty

    init {
        refreshDataFromRepository()
        refreshCryptoDetailFromRepository()

    }


    fun displayDetail(cryptoModel: CryptoModel) {
        _navigateToSelectedProperty.value = cryptoModel
    }

    fun displayDetailComplete() {
        _navigateToSelectedProperty.value = CryptoModel("completed", "a", "i", "s", "s")
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
                }else{
                    _status.value = CryptoApiStatus.OTHER
                }


            }
        }
    }

    private fun refreshCryptoDetailFromRepository() {
        viewModelScope.launch {
            try {
                cryptoListRepository.refreshCryptoDetail()

            } catch (e: java.lang.Exception) {
                Timber.e("Caught exception while it is crypto detail getting  from internet , Exception:  " + e.localizedMessage)
            }
        }
    }

    fun updateCryptoList() {
        refreshDataFromRepository()
    }


}