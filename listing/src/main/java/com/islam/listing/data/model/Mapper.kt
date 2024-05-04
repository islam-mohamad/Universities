package com.islam.listing.data.model

import com.islam.listing.domain.entity.UniversityEntity

fun UniversityModel.toEntity() = UniversityEntity(
    name = name,
    alphaTwoCode = alphaTwoCode?:"",
    country = country?:"",
    domains = domains?: emptyList(),
    stateProvince = stateProvince?:"",
    webPages = webPages?: emptyList()
)