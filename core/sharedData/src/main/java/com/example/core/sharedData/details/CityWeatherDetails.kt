package com.example.core.sharedData.details

data class CityWeatherDetails(
    val humidity: String,
    val temp: String,
    val tempMax: String,
    val tempMin: String,
    val date: String?,
    val icon: String?,
    val main: String?
)
