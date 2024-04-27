package com.example.jetmovie.listing.data.mapper

import com.example.jetmovie.listing.data.api.dto.MovieDto
import com.example.jetmovie.listing.data.api.dto.MovieListDto
import com.example.jetmovie.listing.data.local.movie.MovieDao
import com.example.jetmovie.listing.data.local.movie.MovieEntity
import com.example.jetmovie.listing.domain.model.Movie

fun Movie.toMovieEntity(category: String): MovieEntity {
    return MovieEntity(
        adult = adult,
        backDropPath = backDropPath,
        genreIds = genreIds.joinToString(","),
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAvg = voteAvg,
        voteCount = voteCount,
        category = category,
    )
}

fun MovieEntity.toMovie(): Movie {
    return Movie(
        adult ?: false,
        backDropPath.orEmpty(),
        genreIds = genreIds?.split(",")?.map { it.toIntOrNull() ?: 0 } ?: emptyList(),
        id ?: 0,
        originalLanguage.orEmpty(),
        originalTitle.orEmpty(),
        overview.orEmpty(),
        popularity ?: 0.0,
        posterPath.orEmpty(),
        releaseDate.orEmpty(),
        title.orEmpty(),
        video ?: false,
        voteAvg ?: 0.0,
        voteCount ?: 0,
    )
}


fun MovieDto.toMovieEntity(category: String): MovieEntity {
    return MovieEntity(
        adult = adult,
        backDropPath = backDropPath,
        genreIds = genreIds?.joinToString(",") ?: "",
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAvg = voteAvg,
        voteCount = voteCount,
        category = category,
    )
}