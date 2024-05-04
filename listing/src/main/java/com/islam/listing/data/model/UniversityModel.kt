package com.islam.listing.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "universities")
data class UniversityModel(
    @PrimaryKey @SerializedName("name") val name: String,
    @SerializedName("alpha_two_code") val alphaTwoCode: String?,
    @SerializedName("country") val country: String?,
    @SerializedName("domains") val domains: List<String>?,
    @SerializedName("state-province") val stateProvince: String?,
    @SerializedName("web_pages") val webPages: List<String>?
)