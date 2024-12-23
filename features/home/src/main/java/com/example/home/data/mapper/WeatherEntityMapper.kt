package com.example.home.data.mapper

import com.example.database.entity.MainEntity
import com.example.database.entity.WeatherDetailsEntity
import com.example.database.entity.WeatherEntity
import com.example.home.data.remote.dto.CityWeatherResponse
import com.example.home.data.remote.dto.Main
import com.example.home.data.remote.dto.WeatherResponse
import com.example.network.remoteBase.WeatherIcons
import com.example.utils.mapper.BaseMapper
import javax.inject.Inject


class WeatherEntityMapper @Inject constructor() :
    BaseMapper<CityWeatherResponse?, WeatherEntity?> {
    override fun map(model: CityWeatherResponse?): WeatherEntity? =
        model?.let {
            WeatherEntity(
                country = it.sys?.country,
                city = it?.name,
                cityId = it.id,
                main = it.main?.let(::mapMainTemp),
                weatherDetails = it.weatherResponseList?.let(::mapWeather)
            )
        }


   private fun mapMainTemp(main: Main): MainEntity=
       main.run {
           MainEntity(
               humidity = "$humidity",
               temp = "$temp",
               tempMax = "$tempMax",
               tempMin = "$tempMin"
           )
       }

    private fun mapWeather(weatherList: List<WeatherResponse>): List<WeatherDetailsEntity> =
        weatherList.map {
            WeatherDetailsEntity(
                description = it.description,
                icon = WeatherIcons.getIcon(it.icon),
                main = it.main
            )
        }
}
