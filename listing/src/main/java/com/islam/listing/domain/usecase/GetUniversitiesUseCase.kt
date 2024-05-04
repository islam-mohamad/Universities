package com.islam.listing.domain.usecase

import com.islam.listing.domain.repository.UniversitiesRepository
import javax.inject.Inject

class GetUniversitiesUseCase @Inject constructor(private val repository: UniversitiesRepository) {
    suspend operator fun invoke(param: String) = repository.fetchUniversitiesByCountry(param)
}