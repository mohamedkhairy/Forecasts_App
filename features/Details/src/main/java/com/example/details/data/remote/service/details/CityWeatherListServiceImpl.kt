package com.example.details.data.remote.service.details

import com.example.details.data.remote.dto.CityWeatherListResponse
import com.example.network.remoteBase.Endpoints
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class CityWeatherListServiceImpl @Inject constructor(val httpClient: HttpClient) :
    CityWeatherListService {

    override suspend fun callCityWeatherList(city: String): CityWeatherListResponse? =
        httpClient.get(Endpoints.CITY_WEATHER_LIST_API(city))
            .body<CityWeatherListResponse?>()


}