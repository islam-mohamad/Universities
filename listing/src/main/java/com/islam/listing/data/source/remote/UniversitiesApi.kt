package com.islam.listing.data.source.remote

import com.islam.listing.data.model.UniversityModel
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversitiesApi {
    @GET("search")
    suspend fun getUniversitiesByCountry(@Query("country") country: String): List<UniversityModel>
}