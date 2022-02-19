package com.inflames.curenciesviewmodel.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.inflames.curenciesviewmodel.model.CryptoModel

@Entity(tableName = "crypto_database")
data class CryptoDatabaseModel(
    @PrimaryKey
    val id: String,
    val name: String,
    val symbol: String,
    val logoUrl: String?,
    val price: String
)

fun List<CryptoDatabaseModel>.asDomainModel(): List<CryptoModel> {
    return map {
        CryptoModel(
            id = it.id,
            name = it.name,
            symbol = it.symbol,
            logoUrl = it.logoUrl,
            price = it.price
        )
    }

}
