package com.example.home.fakeData

import com.example.database.entity.WeatherEntity
import com.example.utils.core.jsonParse

object CityWeatherEmpty {
     val emptyData = ""
     val emptyJson = "{}"

     fun emptyWeatherEntity() = emptyData.jsonParse<WeatherEntity>()

}