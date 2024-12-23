package com.example.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Weather_Entity")
data class WeatherEntity(
    @PrimaryKey
    val cityId: Int,
    val country: String?,
    val city: String?,
    @Embedded
    val main: MainEntity?,
    val weatherDetails: List<WeatherDetailsEntity>?
)


data class MainEntity(
    val humidity: String,
    val temp: String,
    val tempMax: String,
    val tempMin: String
)

data class WeatherDetailsEntity(
    val description: String?,
    val icon: String?,
    val main: String?
)
