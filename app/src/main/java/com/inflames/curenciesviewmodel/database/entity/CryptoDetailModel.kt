package com.inflames.curenciesviewmodel.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CryptoDetailModel(
    val id: String,
    val description: String,
    @Json(name = "website_url") val websiteUrl: String,
    @Json(name = "github_url") val githubUrl: String
) : Parcelable

@Entity(tableName = "cryptoDetail")
@Parcelize
data class CryptoDetailDatabaseModel(
    @PrimaryKey(autoGenerate = true)
    val primaryKey: Int = 0,
    val id: String,
    val description: String,
    val websiteUrl: String,
    val githubUrl: String
) : Parcelable {
}


fun List<CryptoDetailModel>.asDatabaseModel(): List<CryptoDetailDatabaseModel> {
    return map {
        CryptoDetailDatabaseModel(
            id = it.id,
            description = it.description,
            websiteUrl = it.websiteUrl,
            githubUrl = it.githubUrl
        )
    }

}