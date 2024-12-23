package com.example.home.domain.useCase

import com.example.core.sharedData.CityWeather
import com.example.home.domain.repository.CityWeatherRepository
import com.example.utils.dispatchers.IoDispatcher
import com.example.utils.usecases.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

open class CityWeatherUseCase @Inject constructor(
    private val cityWeatherRepository: CityWeatherRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<String?, CityWeather?>(ioDispatcher) {

    override suspend fun execute(parameters: String?): Flow<CityWeather?> =
        cityWeatherRepository.getCityWeather(parameters)



}

