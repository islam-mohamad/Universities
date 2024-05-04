package com.islam.listing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.islam.core.di.dispatcher.MainDispatcher
import com.islam.listing.domain.usecase.GetUniversitiesUseCase
import com.islam.listing.presentation.ListingNavigator
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class UniversitiesViewModelFactory @Inject
constructor(
    private val getUniversitiesUseCase: GetUniversitiesUseCase,
    private val navigator: ListingNavigator,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        @Suppress("UNCHECKED_CAST")
        return UniversitiesViewModel(getUniversitiesUseCase, navigator, dispatcher) as T
    }
}