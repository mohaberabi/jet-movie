package com.example.jetmovie.core.util


sealed class AppResult<T>(
    val data: T? = null,
    val message: String? = null
) {


    class Done<T>(data: T) : AppResult<T>(data)
    class Loading<T> : AppResult<T>(null, null)
    class Error<T>(message: String, data: T? = null) : AppResult<T>(message = message, data = null)

}