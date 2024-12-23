package com.example.home.data.remote.service.weather

import android.util.Log
import com.example.home.data.remote.dto.CityWeatherResponse
import com.example.network.remoteBase.Endpoints
import com.example.utils.core.toJsonString
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.request
import javax.inject.Inject

class CityWeatherServiceImpl @Inject constructor(val httpClient: HttpClient) :
    CityWeatherService {

    override suspend fun callCityWeather(city: String): CityWeatherResponse? =
                httpClient.get(Endpoints.WEATHER_API(city))
                    .body<CityWeatherResponse?>()

}