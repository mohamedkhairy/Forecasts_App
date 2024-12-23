package com.example.home.data.mapper

import com.example.core.sharedData.MainTemp
import com.example.core.sharedData.Weather
import com.example.core.sharedData.CityWeather
import com.example.database.entity.MainEntity
import com.example.database.entity.WeatherDetailsEntity
import com.example.database.entity.WeatherEntity
import com.example.home.data.remote.dto.CityWeatherResponse
import com.example.home.data.remote.dto.Main
import com.example.home.data.remote.dto.WeatherResponse
import com.example.network.remoteBase.WeatherIcons
import com.example.utils.mapper.BaseMapper
import javax.inject.Inject


class CityWeatherMapper @Inject constructor() :
    BaseMapper<WeatherEntity?, CityWeather?> {
    override fun map(model: WeatherEntity?): CityWeather? =
        model?.let {
            CityWeather(
                country = it.country,
                city = it.city,
                cityId = it.cityId,
                main = it.main?.let(::mapMainTemp),
                weather = it.weatherDetails?.let(::mapWeather)
            )
        }


   private fun mapMainTemp(main: MainEntity): MainTemp=
       main.run {
           MainTemp(
               humidity = humidity,
               temp = temp,
               tempMax = tempMax,
               tempMin = tempMin
           )
       }

    private fun mapWeather(weatherList: List<WeatherDetailsEntity>): List<Weather> =
        weatherList.map {
            Weather(
                description = it.description,
                icon = it.icon,
                main = it.main
            )
        }
}
