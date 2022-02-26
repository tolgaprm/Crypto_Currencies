package com.inflames.curenciesviewmodel.currencydetailscreen.repository

import androidx.lifecycle.Transformations
import com.inflames.curenciesviewmodel.database.CryptoDatabase

class CryptoDetailRepository(val database: CryptoDatabase, id: String) {

    // it get crypto detail by id data from local database as LiveData
    val cryptoDetail = Transformations.map(database.cryptoDao.getCryptoDetailById(id)) {
        it
    }


}