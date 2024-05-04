package com.islam.listing.domain.entity

data class UniversityEntity(
    val alphaTwoCode: String,
    val country: String,
    val domains: List<String>,
    val name: String,
    val stateProvince: String,
    val webPages: List<String>
)