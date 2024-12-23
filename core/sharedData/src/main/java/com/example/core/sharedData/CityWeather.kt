package com.example.core.sharedData

data class CityWeather(
    val country: String?,
    val city: String?,
    val cityId: Int,
    val main: MainTemp?,
    val weather: List<Weather>?,
)



