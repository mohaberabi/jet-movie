package com.example.jetmovie.listing.data.local.movie

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class MovieDb : RoomDatabase() {

    abstract val movieDao: MovieDao

}