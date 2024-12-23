package com.example.home.data.remote.service.weather

import com.example.home.engine.ForecastFakeClient
import com.example.home.engine.ServiceResponseType
import com.example.home.fakeData.CityWeatherInvalid.invalidCity
import com.example.home.fakeData.CityWeatherValid.expectedCode
import com.example.home.fakeData.CityWeatherValid.validCity
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CityWeatherServiceTest {



    @Test
    fun `test CityWeatherService with success response`() =  runBlocking {
        // setup
        val cityWeatherService = ForecastFakeClient.build(
            type = ServiceResponseType.GoodData // good emptyData
        )

        // execute
        val response = cityWeatherService.callCityWeather(validCity)

        // verify
        Assert.assertEquals(expectedCode, response?.cod)
    }

    @Test
    fun `test CityWeatherService with bad request`() {
        Assert.assertThrows(NoTransformationFoundException::class.java) {
            runBlocking {
                // Code that should throw an exception

                // setup
                val cityWeatherService = ForecastFakeClient.build(
                    type = ServiceResponseType.InvalidData
                )

                // execute
                cityWeatherService.callCityWeather(invalidCity)
            }
        }
    }
    @Test
    fun `test CityWeatherService with invalid response`() {
        Assert.assertThrows(JsonConvertException::class.java) {
            runBlocking {
                // Code that should throw an exception

                // setup
                val cityWeatherService = ForecastFakeClient.build(
                    type = ServiceResponseType.InvalidData
                )

                // execute
                cityWeatherService.callCityWeather(validCity)
            }
        }
    }



    @Test
    fun `test CityWeatherService with not found response`() {
        Assert.assertThrows(JsonConvertException::class.java) {
            runBlocking {
                // Code that should throw an exception

                // setup
                val cityWeatherService = ForecastFakeClient.build(
                    type = ServiceResponseType.Http404
                )

                // execute
                cityWeatherService.callCityWeather(validCity)
            }
        }
    }

    @Test
    fun `test CityWeatherService with Empty response`() {
        Assert.assertThrows(JsonConvertException::class.java) {
            runBlocking {
                // Code that should throw an exception

                // setup
                val cityWeatherService = ForecastFakeClient.build(
                    type = ServiceResponseType.EmptyResponse
                )

                // execute
                cityWeatherService.callCityWeather(validCity)
            }
        }
    }
}