package com.example.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.database.dbManager.DatabaseManager
import com.example.database.dbManager.WeatherDao
import com.example.database.entity.MainEntity
import com.example.database.entity.WeatherDetailsEntity
import com.example.database.entity.WeatherEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@SmallTest
class WeatherDaoTest {

    private lateinit var weatherDao: WeatherDao
    private lateinit var db: DatabaseManager
    private val weather = WeatherEntity(
        cityId = 100,
        country = "EG",
        city = "Cairo",
        main = MainEntity("45", "290", "300", "280"),
        weatherDetails = listOf(WeatherDetailsEntity("clear", "icon", "clear"))
    )

    private val weatherNew = WeatherEntity(
        cityId = 100,
        country = "EG",
        city = "Cairo",
        main = MainEntity("50", "100", "200", "150"),
        weatherDetails = listOf(WeatherDetailsEntity("clouds", "icon", "clouds"))
    )

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, DatabaseManager::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        weatherDao = db.cachedWeatherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetWeather() = runTest {
        weatherDao.save(weather)
        val result = weatherDao.getLatestWeather().first()
        assertEquals(weather.cityId, result?.cityId)
    }


    @Test
    fun updateWeather() = runTest {
        weatherDao.save(weather)
        weatherDao.save(weatherNew)
        val result = weatherDao.getLatestWeather().first()

        assertNotEquals(weather.main?.temp, result?.main?.temp)
        assertEquals(weatherNew.main?.temp, result?.main?.temp)
    }

    @Test
    fun deleteWeather() = runTest {
        weatherDao.save(weather)
        weatherDao.delete()
        val result = weatherDao.getLatestWeather().firstOrNull()

        assertNull(result)
    }


}