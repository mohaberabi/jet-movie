package com.example.jetmovie.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.jetmovie.core.navigation.AppNavHost
import com.example.jetmovie.listing.presentation.viemwodel.ListingViewModel
import com.example.jetmovie.core.theme.JetMovieTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetBarColor(color = MaterialTheme.colorScheme.inverseOnSurface)
            JetMovieTheme {
                val viewModel: ListingViewModel = hiltViewModel()
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }

    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()

        LaunchedEffect(color) {
            systemUiController.setStatusBarColor(color)
        }

    }
}

