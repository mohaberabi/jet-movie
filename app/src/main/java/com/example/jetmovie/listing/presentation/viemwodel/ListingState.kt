package com.example.jetmovie.listing.presentation.viemwodel

import com.example.jetmovie.core.util.MovieCategory
import com.example.jetmovie.listing.domain.model.Movie


enum class ListingStatus {
    INITIAL, LOADING, ERROR, DONE;
}


data class ListingState(
    val state: ListingStatus = ListingStatus.INITIAL,
    val error: String = "",
    val moviesPage: Int = 0,
    val movies: List<Movie> = emptyList(),
    val category: MovieCategory = MovieCategory.POPULAR,
)
