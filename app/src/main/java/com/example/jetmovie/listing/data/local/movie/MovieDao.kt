package com.example.jetmovie.listing.data.local.movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MovieDao {


    @Upsert

    suspend fun upsertMovieList(movies: List<MovieEntity>)


    @Query("SELECT * FROM movies WHERE id=:id")
    suspend fun getMovieById(id: Int): MovieEntity?

    @Query("SELECT * FROM movies WHERE category=:category")
    suspend fun getMoviesByCategory(category: String): List<MovieEntity>

}