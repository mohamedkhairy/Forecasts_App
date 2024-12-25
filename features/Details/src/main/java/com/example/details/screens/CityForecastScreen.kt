package com.example.details.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.sharedData.details.CityForecast
import com.example.core.ui.component.AppLoading
import com.example.core.ui.component.ViewStateMessage
import com.example.core.ui.component.scaffold.ForecastScaffold
import com.example.details.screens.components.WeatherDetailsCard
import com.example.utils.core.UiState
import com.example.utils.core.isNotNull


@Composable
internal fun CityForecastRoute(
    onBackClick: () -> Unit,
    city: String,
    cityForecastViewModel: CityForecastViewModel = hiltViewModel(),
) {

    val cityRemember = remember { mutableStateOf(city) }

    val cityForecastUiState by cityForecastViewModel.cityForecastUiState.collectAsStateWithLifecycle()
    cityForecastViewModel.saveCity(cityRemember.value)

    CityForecastDetailsScreen(
        onBackClick= onBackClick,
        cityForecastUiState = cityForecastUiState,
        city
    )

}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CityForecastDetailsScreen(
    onBackClick: () -> Unit,
    cityForecastUiState: UiState<CityForecast?>,
    city: String
) {
    ForecastScaffold(
        title = city,
        navIcon = {
            Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "Back",
            modifier = Modifier.clickable {
                onBackClick()
            }
        )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(MaterialTheme.colorScheme.background)
            ) {

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.secondary)
                )

                when (cityForecastUiState) {
                    is UiState.Error -> {
                        ViewStateMessage("can't find any result")
                    }

                    is UiState.Ideal -> {}

                    is UiState.Loading -> {
                        AppLoading(cityForecastUiState.isLoading)
                    }

                    is UiState.Success -> {
                        if (cityForecastUiState.data.isNotNull()) {
                            CityWeatherResultView(
                                cityForecast = cityForecastUiState.data!!
                            )
                        } else {
                            ViewStateMessage("can't find any result")
                        }
                    }

                }
            }
        }
    )
}


@Composable
internal fun CityWeatherResultView(
    cityForecast: CityForecast
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {

        Text(
            text = "${cityForecast.city}, ${cityForecast.country}",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        cityForecast.cityWeatherList?.let { weatherList ->
            LazyColumn(
                modifier = Modifier
                    .testTag("WeatherListTag")
                    .fillMaxWidth(),
            ) {
                items(items = weatherList) { weather ->
                    WeatherDetailsCard(cityWeatherDetails = weather)
                }
            }
        }
    }
}






