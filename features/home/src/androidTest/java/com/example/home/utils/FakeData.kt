package com.example.home.utils

import com.example.core.sharedData.home.CityWeather
import com.example.core.sharedData.home.MainTemp
import com.example.core.sharedData.home.Weather

object FakeData {

    val cityWeather = CityWeather(
        country = "EG",
        city = "Cairo",
        cityId = 100,
        main = MainTemp(
            humidity = "50",
            temp = "130",
            tempMax = "150",
            tempMin = "100"
        ),
        weather = listOf(
            Weather(
                description = "",
                icon = "icon",
                main = "clouds"
            )
        )
    )



}