package com.example.home.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityWeatherResponse(
    @SerialName("base")
    val base: String?,
    @SerialName("clouds")
    val clouds: Clouds?,
    @SerialName("cod")
    val cod: Int?,
    @SerialName("coord")
    val coord: Coord?,
    @SerialName("dt")
    val dt: Int?,
    @SerialName("id")
    val id: Int,
    @SerialName("main")
    val main: Main?,
    @SerialName("name")
    val name: String?,
    @SerialName("sys")
    val sys: Sys?,
    @SerialName("timezone")
    val timezone: Int?,
    @SerialName("visibility")
    val visibility: Int?,
    @SerialName("weather")
    val weatherResponseList: List<WeatherResponse>?,
    @SerialName("wind")
    val wind: Wind?
)