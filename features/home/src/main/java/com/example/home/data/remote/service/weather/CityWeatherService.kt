package com.example.home.data.remote.service.weather

import com.example.home.data.remote.dto.CityWeatherResponse

interface CityWeatherService {

    suspend fun callCityWeather(city: String): CityWeatherResponse?


}
