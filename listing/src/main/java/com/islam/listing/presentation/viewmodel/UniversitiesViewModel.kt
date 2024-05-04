package com.islam.listing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.core.di.dispatcher.MainDispatcher
import com.islam.listing.R
import com.islam.listing.domain.entity.UniversityEntity
import com.islam.listing.domain.usecase.GetUniversitiesUseCase
import com.islam.listing.presentation.ListingNavigator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UniversitiesViewModel (
    private val getUniversitiesUseCase: GetUniversitiesUseCase,
    private val navigator: ListingNavigator,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _universitiesStateFlow: MutableStateFlow<List<UniversityEntity>> =
        MutableStateFlow(emptyList())
    val universitiesStateFlow = _universitiesStateFlow.asStateFlow()

    private val _loadingSharedFlow: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val loadingSharedFlow = _loadingSharedFlow.asSharedFlow()

    private val _errorSharedFlow: MutableSharedFlow<Int> = MutableSharedFlow()
    val errorSharedFlow = _errorSharedFlow.asSharedFlow()

    fun getUniversities(country: String) {
        viewModelScope.launch(dispatcher) {
            try {
                _loadingSharedFlow.emit(true)
                val universities = getUniversitiesUseCase(country)
                _universitiesStateFlow.value = universities
                _loadingSharedFlow.emit(false)
            } catch (t: Throwable) {
                _loadingSharedFlow.emit(false)
                _errorSharedFlow.emit(R.string.something_wrong)
            }
        }
    }

    fun navigateToUniversityDetails(university: UniversityEntity) {
        navigator.navigateToDetails(university)
    }

    fun getResultKey() = navigator.getResultKey()
}