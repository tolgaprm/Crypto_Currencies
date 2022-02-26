package com.inflames.curenciesviewmodel.cryptolistscreen.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.inflames.curenciesviewmodel.cryptolistscreen.repository.CryptoListRepository
import com.inflames.curenciesviewmodel.database.CryptoDatabase
import com.inflames.curenciesviewmodel.network.entity.CryptoModel
import kotlinx.coroutines.launch
import timber.log.Timber

enum class CryptoApiStatus {
    LOADING,
    DONE,
    ERROR,
    OTHER
}

class CryptoListViewModel(application: Application) : AndroidViewModel(application) {


    private val _status = MutableLiveData<CryptoApiStatus>()
    val status: LiveData<CryptoApiStatus> get() = _status


    private val cryptoListRepository =
        CryptoListRepository(CryptoDatabase.getDatabase(application.applicationContext))

    var list = cryptoListRepository.cryptos

    // it holds selected crypto item  from recyclerView
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

    // control so that the navigation process does not happen again and again
    fun displayDetailComplete() {
        _navigateToSelectedProperty.value = CryptoModel("completed", "a", "a", "a", "a")
    }

    //  if it have network connect get crypto data from API and save data at local database else get crypto data from local database
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

    // if it have network connect get crypto detail data from API and save data at local database else get crypto detail data from local database
    private fun refreshCryptoDetailFromRepository() {
        viewModelScope.launch {
            try {
                cryptoListRepository.refreshCryptoDetail()
            } catch (e: java.lang.Exception) {
                Timber.e("Caught exception while it is crypto detail getting  from internet , Exception:  " + e.localizedMessage)
            }
        }
    }

    // To update data when using swipeRefreshLayout on fragment_currency_list
    fun updateCryptoList() {
        refreshDataFromRepository()
    }


}