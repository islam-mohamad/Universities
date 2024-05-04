package com.islam.listing.presentation

import com.islam.listing.R
import com.islam.listing.domain.entity.UniversityEntity
import com.islam.listing.domain.usecase.GetUniversitiesUseCase
import com.islam.listing.presentation.viewmodel.UniversitiesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class UniversitiesViewModelTest {

    private val testScheduler = TestCoroutineScheduler()

    @Mock
    private lateinit var getUniversitiesUseCase: GetUniversitiesUseCase

    @Mock
    private lateinit var navigator: ListingNavigator

    private lateinit var dispatcher: TestDispatcher

    private lateinit var viewModel: UniversitiesViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dispatcher = StandardTestDispatcher(testScheduler)
        Dispatchers.setMain(dispatcher)
        viewModel = UniversitiesViewModel(getUniversitiesUseCase, navigator, dispatcher)
    }

    @Test
    fun `test getUniversities success`() = runTest {
        // Given
        whenever(getUniversitiesUseCase(anyString())).thenReturn(universities)
        val loadingObserver = viewModel.loadingSharedFlow.test(this)
        // When
        viewModel.getUniversities(COUNTRY)
        advanceUntilIdle()

        // Then
        loadingObserver.assertValuesAndFinish(true, false)
        assertEquals(viewModel.universitiesStateFlow.value, universities)
    }

    @Test
    fun `test getUniversities failure`() = runTest {
        // Given
        whenever(getUniversitiesUseCase(anyString())).thenThrow(RuntimeException())
        val errorObserver = viewModel.errorSharedFlow.test(this)
        // When
        viewModel.getUniversities(COUNTRY)
        advanceUntilIdle()

        // Then
        errorObserver.assertValuesAndFinish(R.string.something_wrong)
    }

    @Test
    fun `test navigateToUniversityDetails`() {
        // Given
        val university = UniversityEntity(
            alphaTwoCode = "US",
            country = "United States",
            domains = listOf("example.com", "example.edu"),
            name = "Example University",
            stateProvince = "California",
            webPages = listOf("http://example.com", "http://example.edu")
        )

        // When
        viewModel.navigateToUniversityDetails(university)

        // Then
        verify(navigator).navigateToDetails(university)
    }

    @Test
    fun `test getResultKey`() {
        // Given
        val resultKey = "result_key"
        whenever(navigator.getResultKey()).thenReturn(resultKey)

        // When
        val actualResultKey = viewModel.getResultKey()

        // Then
        assert(actualResultKey == resultKey)
    }

    companion object {
        private const val COUNTRY = "United Arab Emirates"
        private val universities = listOf(
            UniversityEntity(
                alphaTwoCode = "US",
                country = "United States",
                domains = listOf("example.com", "example.edu"),
                name = "Example University",
                stateProvince = "California",
                webPages = listOf("http://example.com", "http://example.edu")
            ),
            UniversityEntity(
                alphaTwoCode = "UK",
                country = "United Kingdom",
                domains = listOf("example.ac.uk"),
                name = "Example University UK",
                stateProvince = "London",
                webPages = listOf("http://example.ac.uk")
            ),
        )
    }
}

