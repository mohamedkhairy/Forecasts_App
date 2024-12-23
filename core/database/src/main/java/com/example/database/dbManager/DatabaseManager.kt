package com.example.database.dbManager

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.database.entity.Converters
import com.example.database.entity.WeatherEntity


@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DatabaseManager : RoomDatabase() {

    abstract fun cachedWeatherDao(): WeatherDao

}
