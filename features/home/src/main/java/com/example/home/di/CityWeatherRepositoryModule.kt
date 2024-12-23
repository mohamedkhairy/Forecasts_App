package com.example.home.di

import com.example.database.dbManager.WeatherDao
import com.example.home.data.mapper.CityWeatherMapper
import com.example.home.data.mapper.WeatherEntityMapper
import com.example.home.data.remote.service.weather.CityWeatherService
import com.example.home.data.repository.CityWeatherRepositoryImp
import com.example.home.domain.repository.CityWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CityWeatherRepositoryModule {

    @Provides
    fun provideCityWeatherRepository(
        service: CityWeatherService,
        cityWeatherMapper: CityWeatherMapper,
        dao: WeatherDao,
        entityMapper: WeatherEntityMapper,
    ): CityWeatherRepository =
        CityWeatherRepositoryImp(service, dao,entityMapper, cityWeatherMapper)

}
