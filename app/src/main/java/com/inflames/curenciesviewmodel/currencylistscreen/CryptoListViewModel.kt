package com.inflames.curenciesviewmodel.currencylistscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inflames.curenciesviewmodel.currencylistscreen.repository.CryptoListRepository
import com.inflames.curenciesviewmodel.enums.CryptoApiStatus
import kotlinx.coroutines.launch
import timber.log.Timber


class CryptoListViewModel : ViewModel() {


    private val _status = MutableLiveData<CryptoApiStatus>()
    val status: LiveData<CryptoApiStatus> get() = _status

    private val cryptoListRepository = CryptoListRepository()
    var list = cryptoListRepository.cryptoList
    val cryptoThatSearching = MutableLiveData<String>("")

    init {
        getCryptoList()
    }


    private fun getCryptoList() {
        viewModelScope.launch {
            _status.value = CryptoApiStatus.LOADING
            try {
                cryptoListRepository.getCryptoList()
                _status.value = CryptoApiStatus.DONE

            } catch (e: Exception) {
                Timber.e("Caught exception while it is getting from internet, Exception:  " + e.localizedMessage)
                _status.value = CryptoApiStatus.ERROR

            }


        }
    }

    fun updateCryptoList() {
        cryptoListRepository._cryptoList.value = ArrayList()
        getCryptoList()
    }


}