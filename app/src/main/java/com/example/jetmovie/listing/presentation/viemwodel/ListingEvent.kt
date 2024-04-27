package com.example.jetmovie.listing.presentation.viemwodel

import com.example.jetmovie.core.util.MovieCategory

sealed class ListingEvent {

    data object Paginate : ListingEvent()

    data class OnCategoryChanged(val category: MovieCategory) : ListingEvent()
}