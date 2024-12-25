package com.example.details.data.repository

import com.example.details.data.mapper.CityForecastListMapper
import com.example.details.data.remote.service.details.CityWeatherListService
import com.example.details.domain.repository.CityForecastDetailsRepository
import com.example.details.engine.ForecastFakeClient
import com.example.details.engine.ServiceResponseType
import com.example.details.fakeData.CityForecastDetailsValid.getCityForecast
import com.example.details.fakeData.CityForecastDetailsValid.validCity
import com.example.utils.core.isNotNull
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class CityForecastDetailsRepositoryTest {


    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: CityForecastDetailsRepository
    private lateinit var service: CityWeatherListService
    private lateinit var cityForecastListMapper: CityForecastListMapper


    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        cityForecastListMapper = CityForecastListMapper()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }



    @Test
    fun `getCityWeatherList should return remote data successfully`() = runTest(testDispatcher) {
        // setup
        service = ForecastFakeClient.build(
            type = ServiceResponseType.GoodData
        )

        repository = CityForecastDetailsRepositoryImp(service, cityForecastListMapper)

        val mappedWeather = getCityForecast()

        // execute
        val result = repository.getCityWeatherList(validCity)

        // Assert
        assert(result.isNotNull())
        assertEquals(mappedWeather.cityId, result?.cityId) 
    }

    @Test
    fun `getCityWeatherList should return Error when remote call fails`() = runTest(testDispatcher) {
        // setup
        service = ForecastFakeClient.build(
            type = ServiceResponseType.Http404
        )
        repository = CityForecastDetailsRepositoryImp(service, cityForecastListMapper)

        // Assert
        assertFailsWith<JsonConvertException>{
            repository.getCityWeatherList(validCity)
        }
    }

    @Test
    fun `getCityWeatherList should throw Exception when remote return empty data`() = runTest(testDispatcher) {
        // setup
        service = ForecastFakeClient.build(
            type = ServiceResponseType.EmptyResponse
        )
        repository = CityForecastDetailsRepositoryImp(service, cityForecastListMapper)


        // Assert
        assertFailsWith<JsonConvertException>{
          repository.getCityWeatherList(validCity)
        }
    }
}
