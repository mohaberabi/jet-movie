package com.example.jetmovie.core.util

import com.example.jetmovie.listing.domain.model.Movie

enum class MovieCategory(
    val prefix: String,
    val title: String,
) {
    POPULAR("popular", "Popular"),
    UPCOMING("upcoming", "Upcoming");


    fun switch(): MovieCategory {

        return if (this == POPULAR) {
            UPCOMING
        } else {
            POPULAR
        }
    }
}