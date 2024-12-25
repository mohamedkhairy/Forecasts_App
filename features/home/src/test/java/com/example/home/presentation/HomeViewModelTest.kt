package com.example.home.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.core.sharedData.home.CityWeather
import com.example.home.domain.useCase.CityWeatherUseCase
import com.example.home.fakeData.CityWeatherInvalid.invalidCity
import com.example.home.fakeData.CityWeatherValid.getCityWeather
import com.example.home.fakeData.CityWeatherValid.validCity
import com.example.home.presentation.screens.HomeViewModel
import com.example.home.utils.Constant.SEARCH_QUERY
import com.example.utils.core.CachedEmpty
import com.example.utils.core.NoDataFound
import com.example.utils.core.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var mockCityWeatherUseCase: CityWeatherUseCase
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var homeViewModel: HomeViewModel



    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockCityWeatherUseCase = mock()
        savedStateHandle = SavedStateHandle()
        homeViewModel = HomeViewModel(mockCityWeatherUseCase, savedStateHandle)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `onSearchQueryChanged updates searchQuery in SavedStateHandle`() {

        // execute
        homeViewModel.onSearchQueryChanged(validCity)

        // Assert
        assertEquals(savedStateHandle[SEARCH_QUERY], validCity)
    }

    @Test
    fun `resultUiState emits Ideal when initial value`() = runTest(testDispatcher) {
        // setup
        whenever(mockCityWeatherUseCase(any())).thenReturn(flowOf(UiState.Loading(true)))

        // execute
        homeViewModel.resultUiState.test {

            //Assert
            assertEquals(UiState.Ideal<CityWeather?>(), awaitItem())
            assertEquals(UiState.Loading<CityWeather?>(true), awaitItem())
            cancelAndConsumeRemainingEvents()
        }

    }

    @Test
    fun `resultUiState emits Success when use case emits data`() = runTest(testDispatcher) {
        // setup
        whenever(mockCityWeatherUseCase(any())).thenReturn(flowOf(UiState.Success(getCityWeather())))

        // execute
        homeViewModel.onSearchQueryChanged(validCity)

        homeViewModel.resultUiState.test {
            //Assert
            assertEquals(UiState.Ideal<CityWeather?>(), awaitItem())
            assertEquals(UiState.success(getCityWeather()), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }


    @Test
    fun `resultUiState emits CachedEmpty Error when no cached found`() = runTest {
        // setup
        whenever(mockCityWeatherUseCase("")).thenReturn(flowOf(UiState.Error(CachedEmpty())))

        // execute
        homeViewModel.resultUiState.test {
            //Assert
            assertEquals(UiState.Ideal<CityWeather?>(), awaitItem())
            val result = awaitItem()
            assertTrue { result is UiState.Error<CityWeather?> && result.throwable is CachedEmpty }

            cancelAndConsumeRemainingEvents()
        }
    }


    @Test
    fun `resultUiState emits NoDataFound Error when no result found`() = runTest {
        // setup
        whenever(mockCityWeatherUseCase(invalidCity)).thenReturn(flowOf(UiState.Error(NoDataFound(invalidCity))))

        // execute
        homeViewModel.onSearchQueryChanged(invalidCity)

        homeViewModel.resultUiState.test {
            //Assert
            assertEquals(UiState.Ideal<CityWeather?>(), awaitItem())
            val result = awaitItem()
            assertTrue { result is UiState.Error<CityWeather?> && result.throwable is NoDataFound }

            cancelAndConsumeRemainingEvents()
        }
    }
}