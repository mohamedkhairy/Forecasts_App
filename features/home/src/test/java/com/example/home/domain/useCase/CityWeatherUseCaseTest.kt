package com.example.home.domain.useCase

import com.example.core.sharedData.home.CityWeather
import com.example.database.dbManager.WeatherDao
import com.example.home.data.mapper.CityWeatherMapper
import com.example.home.data.mapper.WeatherEntityMapper
import com.example.home.data.remote.service.weather.CityWeatherService
import com.example.home.data.repository.CityWeatherRepositoryImp
import com.example.home.domain.repository.CityWeatherRepository
import com.example.home.engine.ForecastFakeClient
import com.example.home.engine.ServiceResponseType
import com.example.home.fakeData.CityWeatherValid.getCityWeather
import com.example.home.fakeData.CityWeatherValid.getWeatherEntity
import com.example.home.fakeData.CityWeatherValid.validCity
import com.example.utils.core.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class CityWeatherUseCaseTest {


    private val exceptionMessage = "Cairo"


    private lateinit var repository: CityWeatherRepository
    private lateinit var service: CityWeatherService
    private lateinit var cityWeatherMapper: CityWeatherMapper
    private lateinit var entityMapper: WeatherEntityMapper
    private lateinit var useCase: CityWeatherUseCase
    private val dao: WeatherDao = mock(WeatherDao::class.java)


    @Before
    fun setUp() {
        cityWeatherMapper = CityWeatherMapper()
        entityMapper = WeatherEntityMapper()
    }

    @Test
    fun `test MarvelCharactersUseCase with success data`() =
        runBlocking {
            // Setup
            service = ForecastFakeClient.build(
                type = ServiceResponseType.GoodData
            )
            dao.apply {
                runBlocking {
                    `when`(getLatestWeather()).thenReturn(flowOf(getWeatherEntity()))
                    Mockito.doNothing().`when`(dao).save(getWeatherEntity())
                    Mockito.doNothing().`when`(dao).delete()
                }
            }
            repository = CityWeatherRepositoryImp(service, dao, entityMapper, cityWeatherMapper)
            useCase = CityWeatherUseCase(repository, Dispatchers.IO)

            // Execute
            val result = useCase(validCity).toList()

            // Assert
            Assert.assertEquals(UiState.Loading<CityWeather>(true), result.first())

            Assert.assertEquals(UiState.Success(getCityWeather()), result[1])
        }


    @Test
    fun `test MarvelCharactersUseCase with failure`() =
        runBlocking {
            // Setup
            service = ForecastFakeClient.build(
                type = ServiceResponseType.Http404
            )

            dao.apply {
                runBlocking {
                    `when`(getLatestWeather()).thenReturn(flowOf(getWeatherEntity()))
                    Mockito.doNothing().`when`(dao).save(getWeatherEntity())
                    Mockito.doNothing().`when`(dao).delete()
                }
            }
            repository = CityWeatherRepositoryImp(service, dao, entityMapper, cityWeatherMapper)

            useCase = CityWeatherUseCase(repository, Dispatchers.IO)

            // Execute
            val result = useCase(validCity).toList()
            val error = result[1]

            // Assert
            Assert.assertEquals(result.first(), UiState.Loading<CityWeather>(true))
            assertTrue {error is UiState.Error && exceptionMessage == error.throwable?.message }

        }
}