package com.example.jetmovie.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetmovie.core.presentation.screens.HomeScreen
import com.example.jetmovie.details.presentation.screen.DetailsScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable

fun AppNavHost(
    navController: NavHostController,
    startDestination: String = AppRoutes.HomeRoute.routeName,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(AppRoutes.HomeRoute.routeName) {
            HomeScreen(onMoviePressed = {

                navController.navigate(AppRoutes.DetailsRoute.routeName + "/${it}")
            }
            )
        }
        composable(
            "${AppRoutes.DetailsRoute.routeName}/{${AppNavArgsKeys.movieId}}",
            arguments = listOf(navArgument(AppNavArgsKeys.movieId) {
                type = NavType.IntType

            }),
        ) { entry ->
            DetailsScreen()
        }
    }
}

