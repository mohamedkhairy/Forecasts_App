package com.example.home.domain.repository

import com.example.core.sharedData.home.CityWeather
import kotlinx.coroutines.flow.Flow


interface CityWeatherRepository {
    suspend fun getCityWeather(city: String?): Flow<CityWeather?>

}