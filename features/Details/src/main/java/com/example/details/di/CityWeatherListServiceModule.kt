package com.example.details.di


import com.example.details.data.remote.service.details.CityWeatherListService
import com.example.details.data.remote.service.details.CityWeatherListServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(ViewModelComponent::class)
object CityWeatherListServiceModule {

    @Provides
    fun provideCityWeatherListService(httpClient: HttpClient): CityWeatherListService {
        return CityWeatherListServiceImpl(httpClient)
    }

}