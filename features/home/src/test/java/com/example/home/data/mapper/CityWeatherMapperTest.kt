package com.example.home.data.mapper


import com.example.database.entity.WeatherEntity
import com.example.home.fakeData.CityWeatherEmpty
import com.example.home.fakeData.CityWeatherValid
import com.example.utils.core.jsonParse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class CityWeatherMapperTest {

    private lateinit var cityWeatherMapper: CityWeatherMapper

    @Before
    fun setUp() {
        cityWeatherMapper = CityWeatherMapper()
    }

    @Test
    fun `test CityWeatherMapper maps entity to CityWeather model correctly`() {
        // setup
        val weatherEntity = CityWeatherValid.getWeatherEntity()

        // execute
        val cityWeather = cityWeatherMapper.map(weatherEntity)

        // Assert
        assertEquals(weatherEntity.cityId, cityWeather?.cityId)
        assertEquals(weatherEntity.city, cityWeather?.city)
    }

    @Test
    fun `CityWeatherMapper maps empty entity to CityWeather model`() {
        // setup
        val emptyEntity = CityWeatherEmpty.emptyData.jsonParse<WeatherEntity>()

        // execute
        val cityWeather = cityWeatherMapper.map(emptyEntity)

        // Assert
        assertNull(cityWeather)
    }

}