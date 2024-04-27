package com.example.jetmovie.details.presentation.viewmodel

import com.example.jetmovie.listing.domain.model.Movie


enum class DetailStatus {
    INITIAL, LOADING, ERROR, DONE

}

data class DetailState(
    val movie: Movie? = null,
    val state: DetailStatus = DetailStatus.INITIAL,
    val error: String = ""
)