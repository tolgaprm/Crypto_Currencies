package com.inflames.curenciesviewmodel.model

import android.os.Parcelable
import com.inflames.curenciesviewmodel.database.CryptoDatabaseModel
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CryptoModel(
    val id: String,
    val name: String,
    val symbol: String,
    @Json(name = "logo_url")
    val logoUrl: String?,
    val price: String,
) : Parcelable

fun List<CryptoModel>.asDatabaseModel(): List<CryptoDatabaseModel> {
    return map {
        CryptoDatabaseModel(
            id = it.id,
            name = it.name,
            symbol = it.symbol,
            logoUrl = it.logoUrl,
            price = it.price
        )
    }

}

