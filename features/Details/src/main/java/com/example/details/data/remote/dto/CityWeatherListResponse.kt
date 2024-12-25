package com.example.details.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityWeatherListResponse(
    @SerialName("city")
    val city: City,
    @SerialName("cnt")
    val cnt: Int,
    @SerialName("cod")
    val cod: String,
    @SerialName("list")
    val list: List<Result>?,
    @SerialName("message")
    val message: Int
)