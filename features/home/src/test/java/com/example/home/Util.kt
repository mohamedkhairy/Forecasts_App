package com.example.home


import com.example.database.entity.WeatherEntity
import com.example.home.data.remote.dto.CityWeatherResponse
import kotlinx.serialization.json.Json

private val json = Json {
    ignoreUnknownKeys = true
}

fun serializeWeatherData(jsonData: String): CityWeatherResponse
     = json.decodeFromString<CityWeatherResponse>(jsonData)

fun serializeEntity(jsonData: String): WeatherEntity
        = json.decodeFromString<WeatherEntity>(jsonData)
