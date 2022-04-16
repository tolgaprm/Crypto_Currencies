package com.inflames.curenciesviewmodel.network

import com.inflames.curenciesviewmodel.database.entity.CryptoDetailModel
import com.inflames.curenciesviewmodel.network.entity.CryptoModel
import com.inflames.curenciesviewmodel.util.Constant.Companion.YOUR_API_KEY
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://api.nomics.com/v1/"

interface CryptoAPi {

    @GET("currencies/ticker?key=${YOUR_API_KEY}")
    suspend fun getCryptoList(): List<CryptoModel>

    @GET("currencies?key=${YOUR_API_KEY}&attributes=id,description,website_url,github_url")
    suspend fun getCryptoDetails(): List<CryptoDetailModel>
}

object CryptoService {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val cryptoService: CryptoAPi = retrofit.create(CryptoAPi::class.java)
}