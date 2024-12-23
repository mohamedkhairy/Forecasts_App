package com.example.home.data.mapper


import com.example.home.data.remote.dto.CityWeatherResponse
import com.example.home.fakeData.CityWeatherEmpty
import com.example.home.fakeData.CityWeatherValid
import com.example.utils.core.jsonParse
import com.example.utils.core.toJsonString
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class WeatherEntityMapperTest {

    private lateinit var weatherEntityMapper: WeatherEntityMapper

    @Before
    fun setUp() {
        weatherEntityMapper = WeatherEntityMapper()
    }

    @Test
    fun `test WeatherEntityMapper maps response to WeatherEntity model correctly`() {
        // setup
        val cityWeatherResponse = CityWeatherValid.getCityWeatherResponse()

        println("xxx ---> ${cityWeatherResponse.toJsonString()}")
        // execute
        val weatherEntity = weatherEntityMapper.map(cityWeatherResponse)

        // Assert
        assertEquals(cityWeatherResponse.id, weatherEntity?.cityId)
        assertEquals(cityWeatherResponse.name, weatherEntity?.city)
    }

    @Test
    fun `WeatherEntityMapper maps empty response to WeatherEntity model`() {
        // setup
        val emptyEntity = CityWeatherEmpty.emptyData.jsonParse<CityWeatherResponse>()

        // execute
        val entity = weatherEntityMapper.map(emptyEntity)

        // Assert
        assertNull(entity)
    }

}