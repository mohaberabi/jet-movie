package com.example.jetmovie.details.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetmovie.core.navigation.AppNavArgsKeys
import com.example.jetmovie.core.util.AppResult
import com.example.jetmovie.listing.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {


    private val movieId = savedStateHandle[AppNavArgsKeys.movieId] ?: -1

    private val _state = MutableStateFlow(DetailState())

    val state: StateFlow<DetailState>
        get() = _state

    private fun getMovieById() {


        viewModelScope.launch {
            movieRepository.getMovie(movieId).collect { res ->
                when (res) {
                    is AppResult.Loading -> _state.update { it.copy(state = DetailStatus.LOADING) }

                    is AppResult.Error -> _state.update {
                        it.copy(
                            state = DetailStatus.ERROR,
                            error = res.message ?: ""
                        )
                    }

                    is AppResult.Done -> {
                        _state.update {
                            it.copy(movie = res.data, state = DetailStatus.DONE)
                        }
                    }
                }

            }


        }

    }

    init {
        getMovieById()
    }
}