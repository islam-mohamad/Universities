package com.islam.listing.data.source.local

import com.islam.core.di.dispatcher.IoDispatcher
import com.islam.listing.data.model.UniversityModel
import com.islam.listing.data.model.toEntity
import com.islam.listing.data.source.local.database.UniversityDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UniversitiesDS @Inject constructor(
    private val dao: UniversityDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun insertUniversities(universities: List<UniversityModel>) = withContext(dispatcher) {
        dao.insertUniversities(universities)
    }

    suspend fun getUniversities() = withContext(dispatcher) {
        dao.getUniversities().map { it.toEntity() }
    }

}