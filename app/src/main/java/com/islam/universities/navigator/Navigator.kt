package com.islam.universities.navigator

import com.islam.core.navigation.BaseNavigator
import com.islam.details.UniversityDetailsFragment
import com.islam.details.UniversityUiModel
import com.islam.listing.domain.entity.UniversityEntity
import com.islam.listing.presentation.ListingNavigator
import com.islam.universities.NavGraphDirections
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator @Inject constructor() : BaseNavigator(), ListingNavigator {
    override fun navigateToDetails(university: UniversityEntity) {
        val param = with(university) {
            UniversityUiModel(
                alphaTwoCode = alphaTwoCode,
                country = country,
                name = name,
                stateProvince = stateProvince,
                webPage = if (webPages.isNotEmpty()) webPages[0] else ""
            )
        }
        navController?.navigate(NavGraphDirections.actionListingToUniversitiesDetails(param))
    }

    override fun getResultKey(): String  = UniversityDetailsFragment.RESULT_KEY
}