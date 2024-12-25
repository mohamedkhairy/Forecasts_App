package com.example.details.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("clouds")
    val clouds: Clouds,
    @SerialName("dt")
    val dt: Int,
    @SerialName("dt_txt")
    val dtTxt: String,
    @SerialName("main")
    val main: Main,
    @SerialName("pop")
    val pop: Int,
    @SerialName("sys")
    val sys: Sys,
    @SerialName("visibility")
    val visibility: Int,
    @SerialName("weather")
    val weather: List<Weather>,
    @SerialName("wind")
    val wind: Wind
)