package com.example.details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.details.screens.CityForecastRoute

const val DETAILS_ROUTE_BASE = "details_route"

const val CITY_DETAILS_ARG = "CITY_DETAILS_ARG"

const val DETAILS_ROUTE = "$DETAILS_ROUTE_BASE?$CITY_DETAILS_ARG={$CITY_DETAILS_ARG}"

fun NavController.navigateToCityDetails(city: String?, navOptions: NavOptions? = null) {
    val route = if (!city.isNullOrEmpty()) {
        "${DETAILS_ROUTE_BASE}?${CITY_DETAILS_ARG}=$city"
    } else {
        DETAILS_ROUTE_BASE
    }
    navigate(route, navOptions)
}


fun NavGraphBuilder.cityDetailsScreen(
    onBackClick: () -> Unit
) {
    composable(
        route = DETAILS_ROUTE,
        arguments = listOf(
            navArgument(CITY_DETAILS_ARG) {
                type = NavType.StringType
            },
        ),
    ) {

        val city = it.arguments?.getString(CITY_DETAILS_ARG)
        CityForecastRoute(
            onBackClick = onBackClick,
            city = city ?: "--"
        )
    }
}
