package com.example.network.remoteBase

import com.example.forecasts_app.core.network.BuildConfig


object WeatherIcons {

    private const val BASE_ICON_URL = BuildConfig.BASE_ICON_URL

    fun getIcon(icon: String?): String
            =  "${BASE_ICON_URL}img/wn/${icon ?: "10d"}@2x.png"
}