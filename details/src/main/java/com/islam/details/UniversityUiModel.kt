package com.islam.details

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UniversityUiModel(
    val alphaTwoCode: String,
    val country: String,
    val name: String,
    val stateProvince: String,
    val webPage: String
) : Parcelable