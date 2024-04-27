package com.example.jetmovie.listing.domain.repository

import com.example.jetmovie.core.util.AppResult
import com.example.jetmovie.listing.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {


    suspend fun getMovie(id: Int): Flow<AppResult<Movie>>
    suspend fun getMoviesListByCategory(
        forceRemote: Boolean,
        page: Int,
        category: String,
    ): Flow<AppResult<List<Movie>>>
}