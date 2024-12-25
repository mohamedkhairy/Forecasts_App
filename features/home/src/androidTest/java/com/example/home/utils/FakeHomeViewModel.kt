package com.example.home.utils

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.SavedStateHandle
import com.example.core.sharedData.home.CityWeather
import com.example.home.presentation.screens.HomeViewModel
import com.example.home.utils.Constant.SEARCH_QUERY
import com.example.utils.core.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeHomeViewModel : HomeViewModel(
    cityWeatherUseCase = FakeCityWeatherUseCase(),
    savedStateHandle = SavedStateHandle()
) {

    private val _resultUiState = MutableStateFlow<UiState<CityWeather?>>(UiState.Ideal())

    override val resultUiState: StateFlow<UiState<CityWeather?>> = _resultUiState

    fun setResultUiState(state: UiState<CityWeather?>) {
        _resultUiState.value = state
    }

//    override fun onSearchQueryChanged(query: String?) {
//        savedStateHandle[SEARCH_QUERY] = query
//    }
}
