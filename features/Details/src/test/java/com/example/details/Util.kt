package com.example.details


import com.example.details.data.remote.dto.CityWeatherListResponse
import kotlinx.serialization.json.Json

private val json = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
    isLenient = true
}

fun serializeCityWeatherListResponse(jsonData: String): CityWeatherListResponse
     = json.decodeFromString<CityWeatherListResponse>(jsonData)

