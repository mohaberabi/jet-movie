package com.example.jetmovie.core.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.loader.content.Loader
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetmovie.core.compose.AppBottomBar
import com.example.jetmovie.core.compose.AppLoader
import com.example.jetmovie.core.compose.ErrorCard
import com.example.jetmovie.core.navigation.AppRoutes
import com.example.jetmovie.core.util.MovieCategory
import com.example.jetmovie.listing.presentation.compose.MovieCard
import com.example.jetmovie.listing.presentation.viemwodel.ListingEvent
import com.example.jetmovie.listing.presentation.viemwodel.ListingStatus
import com.example.jetmovie.listing.presentation.viemwodel.ListingViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onMoviePressed: (Int) -> Unit,
    listingViewModel: ListingViewModel = hiltViewModel()
) {
    val state = listingViewModel.state.collectAsState().value
    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text(text = state.category.title)
                },
            )
        },
        bottomBar = {
            AppBottomBar(
                selectedIndex = state.category.ordinal,
                onClick = {
                    val category = MovieCategory.entries[it]
                    if (category != state.category) {
                        listingViewModel.onEvent(ListingEvent.OnCategoryChanged(category))
                    }

                }
            )
        },
    ) { padd ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padd)
        ) {
            when (state.state) {
                ListingStatus.LOADING -> AppLoader()
                ListingStatus.ERROR -> ErrorCard(error = state.error) {
                    listingViewModel.onEvent(ListingEvent.OnCategoryChanged(state.category))
                }

                ListingStatus.DONE -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
                    ) {
                        items(state.movies.size) { index ->
                            val movie = state.movies[index]
                            MovieCard(
                                movie = movie,
                                onPress = {
                                    onMoviePressed(it.id)
                                },
                            )
                            if (state.state != ListingStatus.LOADING && index == state.movies.size - 1) {
                                listingViewModel.onEvent(ListingEvent.Paginate)
                            }
                        }
                    }
                }

                else -> Unit
            }
        }
    }
}