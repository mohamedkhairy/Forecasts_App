package com.example.details.data.mapper

import com.example.core.sharedData.details.CityForecast
import com.example.core.sharedData.details.CityWeatherDetails
import com.example.details.data.remote.dto.CityWeatherListResponse
import com.example.details.data.remote.dto.Result
import com.example.network.remoteBase.WeatherIcons.getIcon
import com.example.utils.core.isNotNull
import com.example.utils.mapper.BaseMapper
import javax.inject.Inject


class CityForecastListMapper @Inject constructor() :
    BaseMapper<CityWeatherListResponse?, CityForecast?> {
    override fun map(model: CityWeatherListResponse?): CityForecast? =
        if (model.isNotNull() && model?.cod == "200") {
            CityForecast(
                country = model.city.country,
                city = model.city.name,
                cityId = model.city.id,
                cityWeatherList = model.list?.let(::mapMainTemp)
            )
        } else null


    private fun mapMainTemp(result: List<Result>?): List<CityWeatherDetails>? =
        result?.map {
            CityWeatherDetails(
                humidity = "${it.main.humidity}",
                temp = "${it.main.temp}",
                tempMax = "${it.main.tempMax}",
                tempMin = "${it.main.tempMin}",
                icon = it.weather.firstOrNull()?.icon.let(::getIcon),
                main = it.weather.firstOrNull()?.main,
                date = it.dtTxt
            )
        }


}
