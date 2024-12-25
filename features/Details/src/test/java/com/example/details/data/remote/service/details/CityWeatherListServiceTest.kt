package com.example.details.data.remote.service.details

import com.example.details.engine.ForecastFakeClient
import com.example.details.engine.ServiceResponseType
import com.example.details.fakeData.CityForecastDetailsInvalid.invalidCity
import com.example.details.fakeData.CityForecastDetailsValid.expectedCode
import com.example.details.fakeData.CityForecastDetailsValid.validCity
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CityWeatherListServiceTest {



    @Test
    fun `test CityWeatherListService with success response`() =  runBlocking {
        // setup
        val cityWeatherService = ForecastFakeClient.build(
            type = ServiceResponseType.GoodData // good emptyData
        )

        // execute
        val response = cityWeatherService.callCityWeatherList(validCity)

        // verify
        Assert.assertEquals(expectedCode, response?.cod)
    }

    @Test
    fun `test CityWeatherListService with bad request`() {
        Assert.assertThrows(NoTransformationFoundException::class.java) {
            runBlocking {
                // Code that should throw an exception

                // setup
                val cityWeatherService = ForecastFakeClient.build(
                    type = ServiceResponseType.InvalidData
                )

                // execute
                cityWeatherService.callCityWeatherList(invalidCity)
            }
        }
    }
    @Test
    fun `test CityWeatherListService with invalid response`() {
        Assert.assertThrows(JsonConvertException::class.java) {
            runBlocking {
                // Code that should throw an exception

                // setup
                val cityWeatherService = ForecastFakeClient.build(
                    type = ServiceResponseType.InvalidData
                )

                // execute
                cityWeatherService.callCityWeatherList(validCity)
            }
        }
    }



    @Test
    fun `test CityWeatherListService with not found response`() {
        Assert.assertThrows(JsonConvertException::class.java) {
            runBlocking {
                // Code that should throw an exception

                // setup
                val cityWeatherService = ForecastFakeClient.build(
                    type = ServiceResponseType.Http404
                )

                // execute
                cityWeatherService.callCityWeatherList(validCity)
            }
        }
    }

    @Test
    fun `test CityWeatherListService with Empty response`() {
        Assert.assertThrows(JsonConvertException::class.java) {
            runBlocking {
                // Code that should throw an exception

                // setup
                val cityWeatherService = ForecastFakeClient.build(
                    type = ServiceResponseType.EmptyResponse
                )

                // execute
                cityWeatherService.callCityWeatherList(validCity)
            }
        }
    }
}