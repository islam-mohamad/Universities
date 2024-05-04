package com.islam.listing.presentation

import com.islam.listing.domain.entity.UniversityEntity

interface ListingNavigator {
    fun navigateToDetails(university: UniversityEntity)
    fun getResultKey():String
}