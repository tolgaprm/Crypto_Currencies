package com.inflames.curenciesviewmodel.model

import com.squareup.moshi.Json

data class CryptoModel(
    val id: String,
    val name: String,
    val symbol: String,
    @Json(name = "logo_url")
    val logoUrl: String?,
    val price: String
)