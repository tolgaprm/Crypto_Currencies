package com.inflames.curenciesviewmodel.currencylistscreen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CryptoListFactory(val app: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("unchecked_cast")
        if (modelClass.isAssignableFrom(CryptoListViewModel::class.java)) {
            return CryptoListViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}