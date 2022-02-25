package com.inflames.curenciesviewmodel

import android.app.Application
import android.os.Build
import androidx.work.*
import com.inflames.curenciesviewmodel.work.RefreshDataWorker
import com.inflames.curenciesviewmodel.work.RefreshDataWorker.Companion.WORK_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

class CryptoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        delayedInit()
    }

    private val applicationScope = CoroutineScope(Dispatchers.IO)

    private fun delayedInit() {
        applicationScope.launch {
            Timber.plant(Timber.DebugTree())
            setupRecurringWork()


        }
    }


    val constraint = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresBatteryNotLow(true)
        .setRequiresCharging(true)
        .apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { // if (Build.VERSION.SDK_INT >= // 23 )
                setRequiresDeviceIdle(true)
            }

        }.build()

    // Update crypto data everyday
    private fun setupRecurringWork() {

        val request = PeriodicWorkRequestBuilder<RefreshDataWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraint)
            .build()

        WorkManager.getInstance().enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )

    }
}