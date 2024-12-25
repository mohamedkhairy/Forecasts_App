package com.example.home.presentation.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.sharedData.home.CityWeather
import com.example.home.domain.useCase.CityWeatherUseCase
import com.example.home.utils.Constant.SEARCH_QUERY
import com.example.utils.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val cityWeatherUseCase: CityWeatherUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {


    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")


    open val resultUiState: StateFlow<UiState<CityWeather?>> =
        searchQuery.flatMapLatest { city ->
            cityWeatherUseCase(city)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Ideal(),
        )

    open fun onSearchQueryChanged(query: String?) {
        savedStateHandle[SEARCH_QUERY] = query
    }


}

