package com.example.home.data.repository

import com.example.database.dbManager.WeatherDao
import com.example.home.data.mapper.CityWeatherMapper
import com.example.home.data.mapper.WeatherEntityMapper
import com.example.home.data.remote.service.weather.CityWeatherService
import com.example.home.domain.repository.CityWeatherRepository
import com.example.home.engine.ForecastFakeClient
import com.example.home.engine.ServiceResponseType
import com.example.home.fakeData.CityWeatherEmpty.emptyWeatherEntity
import com.example.home.fakeData.CityWeatherValid.getCityWeather
import com.example.home.fakeData.CityWeatherValid.getWeatherEntity
import com.example.home.fakeData.CityWeatherValid.validCity
import com.example.utils.core.CachedEmpty
import com.example.utils.core.NoDataFound
import com.example.utils.core.isNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class CityWeatherRepositoryTest {


    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: CityWeatherRepository
    private lateinit var service: CityWeatherService
    private lateinit var cityWeatherMapper: CityWeatherMapper
    private lateinit var entityMapper: WeatherEntityMapper
    private val dao: WeatherDao = mock(WeatherDao::class.java)



    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        cityWeatherMapper = CityWeatherMapper()
        entityMapper= WeatherEntityMapper()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }



    @Test
    fun `getCityWeather should return remote data and save to cache`() = runTest(testDispatcher) {
        // Arrange
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

        val mappedEntity = getWeatherEntity()
        val mappedWeather = getCityWeather()


        // Act
        val result = repository.getCityWeather(validCity).first()

        // Assert
        assert(result.isNotNull())
        assertEquals(mappedWeather.cityId, result?.cityId) // Verify that the result matches the expected emptyData
        assertEquals(dao.getLatestWeather().first()?.cityId, mappedEntity.cityId) // Verify that emptyData was saved to the cache
    }

    @Test
    fun `getCityWeather should return cached emptyData when remote call fails`() = runTest(testDispatcher) {
        // Arrange
        service = ForecastFakeClient.build(
            type = ServiceResponseType.Http404
        )
        dao.apply {
            runBlocking {
                `when`(getLatestWeather()).thenReturn(flowOf(null))
                Mockito.doNothing().`when`(dao).save(getWeatherEntity())
                Mockito.doNothing().`when`(dao).delete()
            }
        }
        repository = CityWeatherRepositoryImp(service, dao, entityMapper, cityWeatherMapper)

        // Assert
        val exception = assertFailsWith<NoDataFound>{
            repository.getCityWeather(validCity).collect()
        }

        Assert.assertEquals(validCity, exception.message)
    }

    @Test
    fun `getCityWeather should throw CachedEmpty when both remote and cache are unavailable`() = runTest(testDispatcher) {
        // Arrange
        service = ForecastFakeClient.build(
            type = ServiceResponseType.EmptyResponse
        )
        dao.apply {
            runBlocking {
                `when`(getLatestWeather()).thenReturn(flowOf(null))
                Mockito.doNothing().`when`(dao).save(emptyWeatherEntity())
                Mockito.doNothing().`when`(dao).delete()
            }
        }
        repository = CityWeatherRepositoryImp(service, dao, entityMapper, cityWeatherMapper)


        // Assert
        assertFailsWith<CachedEmpty>{
          repository.getCityWeather(null).collect()
        }
    }
}
