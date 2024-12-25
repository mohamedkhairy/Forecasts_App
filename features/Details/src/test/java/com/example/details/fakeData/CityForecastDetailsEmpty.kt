package com.example.details.fakeData

import com.example.details.data.remote.dto.CityWeatherListResponse
import com.example.utils.core.jsonParse

object CityForecastDetailsEmpty {
     val emptyData = ""
     val emptyJson = "{}"

     fun emptyCityWeatherListResponse() = emptyJson.jsonParse<CityWeatherListResponse>()


}