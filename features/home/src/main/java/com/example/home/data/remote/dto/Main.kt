package com.example.home.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Main(
    @SerialName("feels_like")
    val feelsLike: Double,
    @SerialName("grnd_level")
    val grndLevel: Int,
    @SerialName("humidity")
    val humidity: String,
    @SerialName("pressure")
    val pressure: String,
    @SerialName("sea_level")
    val seaLevel: Int,
    @SerialName("temp")
    val temp: String,
    @SerialName("temp_max")
    val tempMax: String,
    @SerialName("temp_min")
    val tempMin: String
)