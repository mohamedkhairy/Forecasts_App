package com.example.home.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.home.presentation.screens.HomeScreenRoute

const val HOME_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) =
    navigate(HOME_ROUTE, navOptions)

fun NavGraphBuilder.homeScreen(
    openListClick: (String) -> Unit
) {

    composable(route = HOME_ROUTE) {
        HomeScreenRoute(
            openListClick = openListClick
        )
    }
}
