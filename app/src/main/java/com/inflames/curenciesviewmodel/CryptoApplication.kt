package com.inflames.curenciesviewmodel

import android.app.Application
import timber.log.Timber

class CryptoApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}