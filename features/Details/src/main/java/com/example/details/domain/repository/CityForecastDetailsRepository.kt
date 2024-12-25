package com.example.details.domain.repository

import com.example.core.sharedData.details.CityForecast


interface CityForecastDetailsRepository {

    suspend fun getCityWeatherList(city: String): CityForecast?

}