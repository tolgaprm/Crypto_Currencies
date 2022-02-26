package com.inflames.curenciesviewmodel.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.inflames.curenciesviewmodel.cryptolistscreen.repository.CryptoListRepository
import com.inflames.curenciesviewmodel.database.CryptoDatabase
import timber.log.Timber

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "com.inflames.curenciesviewmodel.work"
    }

    // Automatically refresh Crypto and crypto detail data a daily
    override suspend fun doWork(): Result {
        val database = CryptoDatabase.getDatabase(applicationContext)
        val repositoryList = CryptoListRepository(database)

        try {
            Timber.d("Worker is starting")
            repositoryList.refreshCrypto()
            repositoryList.refreshCryptoDetail()
            Timber.d("Work request for sync is run")
        } catch (e: Exception) {
            Timber.e(e.localizedMessage)
            return Result.retry()

        }

        return Result.success()
    }
}


