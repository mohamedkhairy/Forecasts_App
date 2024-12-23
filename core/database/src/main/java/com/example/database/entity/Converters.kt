package com.example.database.entity

import androidx.room.TypeConverter
import com.example.utils.core.jsonParse
import com.example.utils.core.toJsonString

class Converters {

    @TypeConverter
    fun fromList(value: List<WeatherDetailsEntity>?): String =
        value.toJsonString()

    @TypeConverter
    fun toList(value: String?): List<WeatherDetailsEntity>? =
        value?.jsonParse()
}