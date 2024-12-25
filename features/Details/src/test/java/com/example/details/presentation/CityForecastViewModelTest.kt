package com.example.details.presentation

import android.net.http.HttpException
import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.core.sharedData.details.CityForecast
import com.example.details.domain.useCase.CityForecastDetailsUseCase
import com.example.details.fakeData.CityForecastDetailsValid.getCityForecast
import com.example.details.fakeData.CityForecastDetailsValid.validCity
import com.example.details.screens.CityForecastViewModel
import com.example.details.screens.CityForecastViewModel.Companion.CITY_KEY
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
class CityForecastViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var mockCityForecastDetailsUseCase: CityForecastDetailsUseCase
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var cityForecastViewModel: CityForecastViewModel



    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockCityForecastDetailsUseCase = mock()
        savedStateHandle = SavedStateHandle()
        cityForecastViewModel = CityForecastViewModel(mockCityForecastDetailsUseCase, savedStateHandle)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `saveCity updates city in SavedStateHandle`() {

        // execute
        cityForecastViewModel.saveCity(validCity)

        // Assert
        assertEquals(savedStateHandle[CITY_KEY], validCity)
    }

    @Test
    fun `cityForecastUiState emits Ideal when initial value`() = runTest(testDispatcher) {
        // setup
        whenever(mockCityForecastDetailsUseCase(any())).thenReturn(
            flowOf(UiState.Loading(true))
        )

        // execute
        cityForecastViewModel.saveCity(validCity)

        cityForecastViewModel.cityForecastUiState.test {

            //Assert
            assertEquals(UiState.Ideal<CityForecast?>(), awaitItem())

            assertEquals(UiState.Loading<CityForecast?>(true), awaitItem())
            cancelAndConsumeRemainingEvents()
        }

    }

    @Test
    fun `cityForecastUiState emits Success when use case emits data`() = runTest(testDispatcher) {
        // setup
        whenever(mockCityForecastDetailsUseCase(any())).thenReturn(
            flowOf(UiState.Success(getCityForecast()))
        )

        // execute
        cityForecastViewModel.saveCity(validCity)

        cityForecastViewModel.cityForecastUiState.test {
            //Assert
            assertEquals(UiState.Ideal<CityForecast?>(), awaitItem())
            assertEquals(UiState.success(getCityForecast()), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }


    @Test
    fun `cityForecastUiState emits Error when failure`() = runTest(testDispatcher) {
        // setup
        whenever(mockCityForecastDetailsUseCase(any())).thenReturn(
            flowOf(UiState.Error(HttpException("",null)))
        )

        // execute
        cityForecastViewModel.saveCity(validCity)

        cityForecastViewModel.cityForecastUiState.test {
            //Assert
            assertEquals(UiState.Ideal<CityForecast?>(), awaitItem())
            val result = awaitItem()
            assertTrue { result is UiState.Error && result.throwable is HttpException }

            cancelAndConsumeRemainingEvents()
        }
    }


}