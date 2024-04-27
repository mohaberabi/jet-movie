package com.example.jetmovie.listing.data.repository

import com.example.jetmovie.core.util.AppResult
import com.example.jetmovie.listing.data.api.MovieApi
import com.example.jetmovie.listing.data.local.movie.MovieDao
import com.example.jetmovie.listing.data.mapper.toMovie
import com.example.jetmovie.listing.data.mapper.toMovieEntity
import com.example.jetmovie.listing.domain.model.Movie
import com.example.jetmovie.listing.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val movieApi: MovieApi,
) : MovieRepository {
    override suspend fun getMovie(id: Int): Flow<AppResult<Movie>> {

        return flow {
            emit(AppResult.Loading())
            val movieEntity = movieDao.getMovieById(id)
            if (movieEntity == null) {
                emit(AppResult.Error("Movie can not be found "))

            } else {
                emit(AppResult.Done(movieEntity.toMovie()))

            }
        }
    }

    override suspend fun getMoviesListByCategory(
        forceRemote: Boolean,
        page: Int,
        category: String
    ): Flow<AppResult<List<Movie>>> {

        return flow {
            emit(AppResult.Loading())
            val local = movieDao.getMoviesByCategory(category)
            val getFromLocal = local.isNotEmpty() && !forceRemote
            if (getFromLocal) {
                emit(AppResult.Done(local.map { it.toMovie() }))
                return@flow
            }

            val remote = try {
                movieApi.getMoviesList(category, page)
            } catch (e: Exception) {
                e.printStackTrace()
                emit(AppResult.Error(e.toString()))
                return@flow
            }

            val entities = remote.results.let {
                it.map { dto ->
                    dto.toMovieEntity(category)
                }
            }
            movieDao.upsertMovieList(entities)
            emit(AppResult.Done(entities.map { it.toMovie() }))
        }
    }

}