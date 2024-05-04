package com.islam.listing.domain.repository

import com.islam.listing.domain.entity.UniversityEntity

interface UniversitiesRepository {
    suspend fun fetchUniversitiesByCountry(country: String): List<UniversityEntity>
}