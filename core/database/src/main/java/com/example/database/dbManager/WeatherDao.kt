package com.example.database.dbManager

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.database.entity.WeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM Weather_Entity")
    fun getLatestWeather(): Flow<WeatherEntity?>


    @Upsert
    fun save(latestWeatherResult: WeatherEntity)

    @Query("DELETE FROM weather_entity")
    fun delete()

}