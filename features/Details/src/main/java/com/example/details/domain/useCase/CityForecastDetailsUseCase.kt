package com.example.details.domain.useCase

import com.example.core.sharedData.details.CityForecast
import com.example.details.domain.repository.CityForecastDetailsRepository
import com.example.utils.dispatchers.IoDispatcher
import com.example.utils.usecases.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CityForecastDetailsUseCase @Inject constructor(
    private val cityForecastDetailsRepository: CityForecastDetailsRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : FlowUseCase<String, CityForecast?>(ioDispatcher) {

    override suspend fun execute(parameters: String): Flow<CityForecast?> = flow {
        emit(cityForecastDetailsRepository.getCityWeatherList(parameters))
    }

}

