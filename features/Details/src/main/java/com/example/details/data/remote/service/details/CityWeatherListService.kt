package com.example.details.data.remote.service.details

import com.example.details.data.remote.dto.CityWeatherListResponse

interface CityWeatherListService {

    suspend fun callCityWeatherList(city: String): CityWeatherListResponse?


}