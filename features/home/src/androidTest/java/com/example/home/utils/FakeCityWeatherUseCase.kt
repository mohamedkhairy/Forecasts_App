package com.example.home.utils

import com.example.core.sharedData.home.CityWeather
import com.example.home.domain.useCase.CityWeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.mockito.kotlin.mock

class FakeCityWeatherUseCase : CityWeatherUseCase(mock() , Dispatchers.IO) {

    override suspend fun execute(parameters: String?): Flow<CityWeather?> {
        return flowOf(
            FakeData.cityWeather
        )
    }
}