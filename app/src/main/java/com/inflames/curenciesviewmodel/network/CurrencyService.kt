package com.inflames.curenciesviewmodel.network

import com.inflames.curenciesviewmodel.model.CryptoDetailModel
import com.inflames.curenciesviewmodel.model.CryptoModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.nomics.com/v1/"

interface CryptoAPi {

    @GET("currencies/ticker?key=c234e710fc12ead56499cc895b685f2a4661b6d9")
    suspend fun getCryptoList(): List<CryptoModel>

    @GET("currencies?key=c234e710fc12ead56499cc895b685f2a4661b6d9&attributes=id,description,website_url,github_url")
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