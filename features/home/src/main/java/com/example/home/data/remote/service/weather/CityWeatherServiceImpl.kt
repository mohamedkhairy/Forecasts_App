package com.example.home.data.remote.service.weather

import com.example.home.data.remote.dto.CityWeatherResponse
import com.example.network.remoteBase.Endpoints
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class CityWeatherServiceImpl @Inject constructor(val httpClient: HttpClient) :
    CityWeatherService {

    override suspend fun callCityWeather(city: String): CityWeatherResponse? =
                httpClient.get(Endpoints.WEATHER_API(city))
                    .body<CityWeatherResponse?>()

}