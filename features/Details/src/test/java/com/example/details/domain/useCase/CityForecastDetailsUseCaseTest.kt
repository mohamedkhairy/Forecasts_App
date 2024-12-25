package com.example.details.domain.useCase

import com.example.core.sharedData.home.CityWeather
import com.example.details.data.mapper.CityForecastListMapper
import com.example.details.data.remote.service.details.CityWeatherListService
import com.example.details.data.repository.CityForecastDetailsRepositoryImp
import com.example.details.domain.repository.CityForecastDetailsRepository
import com.example.details.engine.ForecastFakeClient
import com.example.details.engine.ServiceResponseType
import com.example.details.fakeData.CityForecastDetailsValid.getCityForecast
import com.example.details.fakeData.CityForecastDetailsValid.validCity
import com.example.utils.core.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class CityForecastDetailsUseCaseTest {

    private lateinit var repository: CityForecastDetailsRepository
    private lateinit var service: CityWeatherListService
    private lateinit var cityForecastListMapper: CityForecastListMapper
    private lateinit var useCase: CityForecastDetailsUseCase


    @Before
    fun setUp() {
        cityForecastListMapper = CityForecastListMapper()
    }

    @Test
    fun `test CityForecastDetailsUseCase with success data`() =
        runBlocking {
            // Setup
            service = ForecastFakeClient.build(
                type = ServiceResponseType.GoodData
            )
            repository = CityForecastDetailsRepositoryImp(service, cityForecastListMapper)

            useCase = CityForecastDetailsUseCase(repository, Dispatchers.IO)

            // Execute
            val result = useCase(validCity).toList()

            // Assert
            Assert.assertEquals(UiState.Loading<CityWeather>(true), result.first())
            Assert.assertEquals(UiState.Success(getCityForecast()), result[1])
        }


    @Test
    fun `test CityForecastDetailsUseCase with failure`() =
        runBlocking {
            // Setup
            service = ForecastFakeClient.build(
                type = ServiceResponseType.Http404
            )

            repository = CityForecastDetailsRepositoryImp(service, cityForecastListMapper)

            useCase = CityForecastDetailsUseCase(repository, Dispatchers.IO)

            // Execute
            val result = useCase(validCity).toList()
            val error = result[1]

            // Assert
            Assert.assertEquals(result.first(), UiState.Loading<CityWeather>(true))
            assertTrue {error is UiState.Error}

        }
}