package com.example.home.di

import com.example.database.dbManager.DatabaseManager
import com.example.database.dbManager.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object WeatherDaoModule {

    @Provides
    fun provideWeatherDao(database: DatabaseManager): WeatherDao =
        database.cachedWeatherDao()

}