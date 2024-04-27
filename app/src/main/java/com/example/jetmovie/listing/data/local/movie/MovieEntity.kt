package com.example.jetmovie.listing.data.local.movie

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "movies")
data class MovieEntity(
    val adult: Boolean?,
    val backDropPath: String?,
    val genreIds: String?,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAvg: Double?,
    val voteCount: Int?,
    val category: String
)
