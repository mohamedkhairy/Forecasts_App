package com.example.details.di

import com.example.details.data.mapper.CityForecastListMapper
import com.example.details.data.remote.service.details.CityWeatherListService
import com.example.details.data.repository.CityForecastDetailsRepositoryImp
import com.example.details.domain.repository.CityForecastDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object CityForecastDetailsRepositoryModule {

    @Provides
    fun provideCityForecastDetailsRepository(
        service: CityWeatherListService,
        cityForecastListMapper: CityForecastListMapper
    ): CityForecastDetailsRepository =
        CityForecastDetailsRepositoryImp(service, cityForecastListMapper)

}
