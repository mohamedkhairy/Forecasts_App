package com.example.details.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.sharedData.details.CityForecast
import com.example.details.domain.useCase.CityForecastDetailsUseCase
import com.example.utils.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CityForecastViewModel @Inject constructor(
    private val cityForecastDetailsUseCase: CityForecastDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val cityState = savedStateHandle.getStateFlow(key = CITY_KEY, initialValue = "")

    val cityForecastUiState: StateFlow<UiState<CityForecast?>> =
        cityState.flatMapLatest { city ->
                if (city.isEmpty()) {
                    flowOf(UiState.Ideal())
                } else {
                    cityForecastDetailsUseCase(city)
                }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Ideal(),
        )


    fun saveCity(city: String) {
        savedStateHandle[CITY_KEY] = city
    }


    companion object{
        const val CITY_KEY = "CITY_KEY"
    }
}
