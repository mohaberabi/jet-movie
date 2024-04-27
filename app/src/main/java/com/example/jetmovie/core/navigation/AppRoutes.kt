package com.example.jetmovie.core.navigation


object AppNavArgsKeys {


    const val movieId = "movieId"
}


private object AppRoutesNames {


    const val HOME_ROUTE = "home"
    const val DETAILS_ROUTE = "details"

}

sealed class AppRoutes(val routeName: String) {


    data object HomeRoute : AppRoutes(AppRoutesNames.HOME_ROUTE)
    data object DetailsRoute : AppRoutes(AppRoutesNames.DETAILS_ROUTE)

}