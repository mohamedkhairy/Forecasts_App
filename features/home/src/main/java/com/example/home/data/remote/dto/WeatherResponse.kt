package com.example.home.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("description")
    val description: String?,
    @SerialName("icon")
    val icon: String?,
    @SerialName("id")
    val id: Int,
    @SerialName("main")
    val main: String?
)