package com.example.jetmovie.listing.domain.model

import com.google.gson.annotations.SerializedName


data class Movie(
    val adult: Boolean,
    val backDropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAvg: Double,
    val voteCount: Int
)