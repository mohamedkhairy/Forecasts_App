package com.example.home.data.repository

import com.example.core.sharedData.home.CityWeather
import com.example.database.dbManager.WeatherDao
import com.example.home.data.mapper.CityWeatherMapper
import com.example.home.data.mapper.WeatherEntityMapper
import com.example.home.data.remote.service.weather.CityWeatherService
import com.example.home.domain.repository.CityWeatherRepository
import com.example.utils.core.CachedEmpty
import com.example.utils.core.NoDataFound
import com.example.utils.handler.cachingHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CityWeatherRepositoryImp @Inject constructor(
    private val service: CityWeatherService,
    private val dao: WeatherDao,
    private val entityMapper: WeatherEntityMapper,
    private val cityWeatherMapper: CityWeatherMapper,
) : CityWeatherRepository {


    override suspend fun getCityWeather(city: String?): Flow<CityWeather?> =
        cachingHandler(
            remoteSource = {
                city?.let { service.callCityWeather(it) }
            },
            saveResult = {
                entityMapper.map(it)?.let { weatherEntity ->
                    dao.delete()
                    dao.save(weatherEntity)
                }
            },
            localSource = { isSuccessful ->
                if (city.isNullOrEmpty() || isSuccessful) {
                    dao.getLatestWeather().map {
                        it?.let(cityWeatherMapper::map)?: run { throw CachedEmpty() }
                    }
                } else throw NoDataFound(city)
            }
        )

}