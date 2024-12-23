package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.dbManager.DatabaseManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DatabaseManager =
        Room.databaseBuilder(context, DatabaseManager::class.java, "forecast_database")
            .build()


}