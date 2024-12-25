package com.example.details.data.repository

import com.example.core.sharedData.details.CityForecast
import com.example.details.data.mapper.CityForecastListMapper
import com.example.details.data.remote.service.details.CityWeatherListService
import com.example.details.domain.repository.CityForecastDetailsRepository
import javax.inject.Inject

class CityForecastDetailsRepositoryImp @Inject constructor(
    private val service: CityWeatherListService,
    private val cityForecastListMapper: CityForecastListMapper,
    ) : CityForecastDetailsRepository {

    override suspend fun getCityWeatherList(city: String): CityForecast? =
        service.callCityWeatherList(city)?.let(cityForecastListMapper::map)

}