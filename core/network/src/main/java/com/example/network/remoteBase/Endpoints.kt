package com.example.network.remoteBase

import com.example.forecasts_app.core.network.BuildConfig
import io.ktor.http.URLBuilder
import io.ktor.http.appendPathSegments

object Endpoints {

    private const val BASE_URL = BuildConfig.BASE_URL
    private const val WEATHER_URL = "data/2.5/weather"
    private const val CITY_WEATHER_LIST_URL = "data/2.5/forecast"
    private val API_KEY = BuildConfig.API_KEY

    fun WEATHER_API(city: String) =
        URLBuilder(BASE_URL).apply {
            appendPathSegments(WEATHER_URL)
            parameters.append("q", city)
            parameters.append("apikey", API_KEY)
        }.build()


    fun CITY_WEATHER_LIST_API(city: String) =
        URLBuilder(BASE_URL).apply {
            appendPathSegments(CITY_WEATHER_LIST_URL)
            parameters.append("q", city)
            parameters.append("cnt", "7")
            parameters.append("apikey", API_KEY)
        }.build()

}