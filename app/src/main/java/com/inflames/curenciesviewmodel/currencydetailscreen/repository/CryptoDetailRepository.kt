package com.inflames.curenciesviewmodel.currencydetailscreen.repository

import androidx.lifecycle.Transformations
import com.inflames.curenciesviewmodel.database.CryptoDatabase

class CryptoDetailRepository(val database: CryptoDatabase, id: String) {

    val cryptoDetail = Transformations.map(database.cryptoDao.getCryptoDetailById(id)) {
        it
    }


}