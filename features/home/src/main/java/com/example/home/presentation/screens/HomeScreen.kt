package com.example.home.presentation.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.sharedData.CityWeather
import com.example.core.ui.component.AppLoading
import com.example.core.ui.component.CoilImagePainter
import com.example.core.ui.component.ViewStateMessage
import com.example.core.ui.component.scaffold.ForecastScaffold
import com.example.home.presentation.components.WeatherItem
import com.example.utils.core.CachedEmpty
import com.example.utils.core.NoDataFound
import com.example.utils.core.UiState
import com.example.utils.core.isNotNull
import com.example.utils.core.toJsonString


@Composable
internal fun HomeScreenRoute(
    openListClick: (String) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val resultUiState by homeViewModel.resultUiState.collectAsStateWithLifecycle()
    val searchQuery by homeViewModel.searchQuery.collectAsStateWithLifecycle()


    HomeSearchScreen(
        openListClick = openListClick,
        searchQuery = searchQuery,
        resultUiState = resultUiState,
        onSearchQueryChanged = homeViewModel::onSearchQueryChanged,
    )
}

@Composable
fun HomeSearchScreen(
    openListClick: (String) -> Unit,
    searchQuery: String,
    resultUiState: UiState<CityWeather?>,
    onSearchQueryChanged: (String?) -> Unit = {},
) {
    var searchText by remember { mutableStateOf(searchQuery) }
    ForecastScaffold(
        title = "Marvel Characters",
        content = {
            Column(
                modifier = Modifier
                    .testTag("MainBox")
                    .fillMaxSize()
                    .padding(it)
                    .background(MaterialTheme.colorScheme.background)
            ) {

                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        onSearchQueryChanged(it)
                    },
                    label = { Text("Enter city name") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {
                        onSearchQueryChanged(searchText)
                    }),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                when (resultUiState) {
                    is UiState.Error -> {
                        when (resultUiState.throwable) {
                            is NoDataFound -> ViewStateMessage("No data found for '${resultUiState.throwable?.message}'")
                            is CachedEmpty -> ViewStateMessage("Enter city to view the weather")
                            else -> ViewStateMessage("Error happened, try again later")
                        }
                    }

                    is UiState.Ideal -> {}

                    is UiState.Loading -> {
                        AppLoading(resultUiState.isLoading)
                    }

                    is UiState.Success -> {
                        if (resultUiState.data.isNotNull()) {
                            CityWeatherResultView(
                                openListClick = openListClick,
                                cityWeather = resultUiState.data!!,
                            )
                        } else {
                            ViewStateMessage("can't find any result")
                        }
                    }

                }

            }


        },

        )


}

@Composable
internal fun CityWeatherResultView(
    openListClick: (String) -> Unit,
    cityWeather: CityWeather,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {

        Text(
            text = "${cityWeather.city}, ${cityWeather.country}",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Temperature: ${cityWeather.main?.temp}",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Max: ${cityWeather.main?.tempMax}",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Min: ${cityWeather.main?.tempMin}",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Humidity: ${cityWeather.main?.humidity}",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .padding(vertical = 8.dp)
                .background(Color.Gray)
        )

        cityWeather.weather?.let {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(it) { data ->
                    WeatherItem(weather = data)
                }
            }
        }


        Text(
            text = "${cityWeather.city} Forecast List",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .border(border = BorderStroke(1.dp, Color.LightGray))
                .clip(RoundedCornerShape(8.dp))
                .padding(16.dp)
                .clickable {
                    Log.d("xxx", "${cityWeather.toJsonString()}")
                }
        )
    }

}

