package com.example.home.di


import com.example.home.data.remote.service.weather.CityWeatherService
import com.example.home.data.remote.service.weather.CityWeatherServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(ViewModelComponent::class)
object CityWeatherServiceModule {

    @Provides
    fun provideCityWeatherService(httpClient: HttpClient): CityWeatherService {
        return CityWeatherServiceImpl(httpClient)
    }

}