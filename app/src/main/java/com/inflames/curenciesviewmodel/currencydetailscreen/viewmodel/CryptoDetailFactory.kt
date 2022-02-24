package com.inflames.curenciesviewmodel.currencydetailscreen.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.inflames.curenciesviewmodel.model.CryptoModel

class CryptoDetailFactory(val app: Application, private val clickedCryptoModelById: CryptoModel) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CryptoDetailViewModel::class.java)) {
            return CryptoDetailViewModel(app, clickedCryptoModelById) as T
        }
        throw IllegalArgumentException("unknown ViewModel Class")
    }
}