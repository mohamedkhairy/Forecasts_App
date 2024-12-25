package com.example.core.sharedData.details

data class CityForecast(
    val country: String?,
    val city: String?,
    val cityId: Int,
    val cityWeatherList: List<CityWeatherDetails>?,
)
