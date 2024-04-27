package com.example.jetmovie.core.di

import android.app.Application
import androidx.room.Room
import com.example.jetmovie.listing.data.api.MovieApi
import com.example.jetmovie.listing.data.api.dto.MovieDto
import com.example.jetmovie.listing.data.local.movie.MovieDao
import com.example.jetmovie.listing.data.local.movie.MovieDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder().baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                client
            ).build().create(MovieApi::class.java)
    }


    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDb {
        return Room.databaseBuilder(app, MovieDb::class.java, "movie.db").build()
    }


    @Provides
    @Singleton
    fun provideMovieDao(movieDb: MovieDb): MovieDao = movieDb.movieDao
}
