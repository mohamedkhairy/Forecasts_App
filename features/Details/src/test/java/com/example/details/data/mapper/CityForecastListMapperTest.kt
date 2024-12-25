package com.example.details.data.mapper


import com.example.details.fakeData.CityForecastDetailsEmpty
import com.example.details.fakeData.CityForecastDetailsValid
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class CityForecastListMapperTest {

    private lateinit var cityForecastListMapper: CityForecastListMapper


    @Before
    fun setUp() {
        cityForecastListMapper = CityForecastListMapper()
    }

    @Test
    fun `test CityForecastListMapper maps response to CityForecast model correctly`() {
        // setup
        val response = CityForecastDetailsValid.getCityWeatherListResponse()

        // execute
        val cityForecast = cityForecastListMapper.map(response)

        // Assert
        assertEquals(response.city.id, cityForecast?.cityId)
        assertEquals(response.city.name, cityForecast?.city)
    }

    @Test
    fun `CityWeatherMapper maps empty response, assert return null model`() {
        // setup
        val emptyResponse = CityForecastDetailsEmpty.emptyCityWeatherListResponse()

        // execute
        val cityWeather = cityForecastListMapper.map(emptyResponse)

        // Assert
        assertNull(cityWeather)
    }

    @Test
    fun `CityForecastListMapper maps emptyList response, asset the list is empty`() {
        // setup
        val emptyResponse = CityForecastDetailsValid.getEmptyWeatherListResultResponse()

        // execute
        val cityWeather = cityForecastListMapper.map(emptyResponse)

        // Assert
        assertTrue {cityWeather?.cityWeatherList.isNullOrEmpty()}
    }

}