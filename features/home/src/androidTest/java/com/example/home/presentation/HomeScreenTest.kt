package com.example.home.presentation

import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.home.presentation.screens.HomeScreenRoute
import com.example.home.utils.FakeData.cityWeather
import com.example.home.utils.ForecastTestBase
import com.example.utils.core.CachedEmpty
import com.example.utils.core.NoDataFound
import com.example.utils.core.UiState
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test


@HiltAndroidTest
class HomeScreenTest : ForecastTestBase() {




    @Test
    fun homeScreen_showsLoadingState() {
        with(composeTestRule) {
            // Given the ViewModel is in the loading state
            fakeViewModel.setResultUiState(UiState.Loading(isLoading = true))

            // When the HomeScreenRoute is displayed
            setContent {
                HomeScreenRoute(openListClick = {}, homeViewModel = fakeViewModel)
            }

            // Then the loading UI is displayed
            onNodeWithTag("loading").assertExists()
        }
    }

    @Test
    fun homeScreen_showsEmptyState_whenEmptyData() {
        with(composeTestRule) {
            // Given the ViewModel returns an empty list
            fakeViewModel.setResultUiState(UiState.Success(null))

            // When the HomeScreenRoute is displayed
            setContent {
                HomeScreenRoute(openListClick = {}, homeViewModel = fakeViewModel)
            }

            // Then the empty state message is displayed
            onNodeWithText("can't find any result").assertExists()
        }
    }

    @Test
    fun homeScreen_showsErrorState_whenNoDataFound() {
        with(composeTestRule) {
            // Given the ViewModel returns an empty list
            fakeViewModel.setResultUiState(UiState.Error(NoDataFound("d")))

            // When the HomeScreenRoute is displayed
            setContent {
                HomeScreenRoute(openListClick = {}, homeViewModel = fakeViewModel)
            }

            // Then the empty state message is displayed
            onNodeWithText("No data found for 'd'").assertExists()
        }
    }

    @Test
    fun homeScreen_showsErrorState_when_CachedEmpty() {
        with(composeTestRule) {
            // Given the ViewModel returns an empty list
            fakeViewModel.setResultUiState(UiState.Error(CachedEmpty()))

            // When the HomeScreenRoute is displayed
            setContent {
                HomeScreenRoute(openListClick = {}, homeViewModel = fakeViewModel)
            }

            // Then the empty state message is displayed
            onNodeWithText("Enter city to view the weather").assertExists()
        }
    }

    @Test
    fun homeScreen_showsErrorState_when_unexpectedError() {
        with(composeTestRule) {
            // Given the ViewModel returns an empty list
            fakeViewModel.setResultUiState(UiState.Error(Exception()))

            // When the HomeScreenRoute is displayed
            setContent {
                HomeScreenRoute(openListClick = {}, homeViewModel = fakeViewModel)
            }

            // Then the empty state message is displayed
            onNodeWithText("Error happened, try again later").assertExists()
        }
    }

    @Test
    fun homeScreen_showWeatherList() {
        with(composeTestRule) {

            // Given the ViewModel returns a list of characters

            fakeViewModel.setResultUiState(UiState.Success(cityWeather))

            // When the HomeScreenRoute is displayed
            setContent {
                HomeScreenRoute(openListClick = {}, homeViewModel = fakeViewModel)
            }

            // Then the characters are displayed in the list
            onNodeWithTag("clouds").assertExists()
        }
    }

    @Test
    fun homeScreen_callsOnCharacterClick_whenCharacterIsClicked() {
        with(composeTestRule) {
            // Given the ViewModel returns a list of characters


            fakeViewModel.setResultUiState(UiState.Success(cityWeather))

            var openCityName: String? = null

            // When the HomeScreenRoute is displayed and a character is clicked
            setContent {
                HomeScreenRoute(
                    openListClick = {
                        openCityName = it
                        assert(openCityName == cityWeather.city)
                    },
                    homeViewModel = fakeViewModel
                )
            }

            onNodeWithTag("openCityForecast").performClick()
            waitForIdle()
        }
    }
}
