package com.example.home.domain.repository

import com.example.core.sharedData.CityWeather
import kotlinx.coroutines.flow.Flow


interface CityWeatherRepository {
    suspend fun getCityWeather(city: String?): Flow<CityWeather?>

}