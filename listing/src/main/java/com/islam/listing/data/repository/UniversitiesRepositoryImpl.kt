package com.islam.listing.data.repository

import com.islam.core.di.dispatcher.IoDispatcher
import com.islam.listing.data.model.toEntity
import com.islam.listing.data.source.local.UniversitiesDS
import com.islam.listing.data.source.remote.UniversitiesApi
import com.islam.listing.domain.entity.UniversityEntity
import com.islam.listing.domain.repository.UniversitiesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class UniversitiesRepositoryImpl @Inject constructor(
    private val api: UniversitiesApi,
    private val universitiesDS: UniversitiesDS,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : UniversitiesRepository {

    override suspend fun fetchUniversitiesByCountry(country: String): List<UniversityEntity> {
        return withContext(dispatcher) {
            try {
                val universities = api.getUniversitiesByCountry(country)
                universitiesDS.insertUniversities(universities)
                universities.map { it.toEntity() }
            } catch (t: Throwable) {
                Timber.e(t, "Error fetching universities from API")
                universitiesDS.getUniversities()
            }
        }
    }
}
