package com.example.forecasts_app

import android.app.Application
import coil.Coil
import coil.ImageLoader
import coil.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ForecastBaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Coil.setImageLoader(
            ImageLoader.Builder(this)
                .logger(DebugLogger())
                .build()
        )
    }
}