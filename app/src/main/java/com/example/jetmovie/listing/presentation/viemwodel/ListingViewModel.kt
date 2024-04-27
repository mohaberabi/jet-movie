package com.example.jetmovie.listing.presentation.viemwodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetmovie.core.util.AppResult
import com.example.jetmovie.core.util.MovieCategory
import com.example.jetmovie.listing.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class ListingViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) :
    ViewModel() {
    private val _state = MutableStateFlow(ListingState())

    val state: StateFlow<ListingState>
        get() = _state

    init {
        getMovies()
    }

    fun onEvent(event: ListingEvent) {
        when (event) {
            is ListingEvent.OnCategoryChanged -> {
                _state.update {
                    it.copy(
                        category = event.category
                    )
                }
                getMovies()
            }

            is ListingEvent.Paginate -> getMovies(shouldShowLoading = false)
        }
    }

    private fun getMovies(
        forceRemote: Boolean = false,
        shouldShowLoading: Boolean = true,
    ) {

        if (shouldShowLoading)
            _state.update {
                it.copy(
                    state = ListingStatus.LOADING
                )
            }
        viewModelScope.launch {
            movieRepository.getMoviesListByCategory(
                forceRemote,
                _state.value.moviesPage + 1,
                _state.value.category.prefix
            ).collectLatest { res ->
                when (res) {
                    is AppResult.Loading -> _state.update {
                        it.copy(
                            state = if (shouldShowLoading) ListingStatus.LOADING
                            else it.state
                        )
                    }

                    is AppResult.Error -> _state.update {
                        it.copy(
                            state = ListingStatus.ERROR,
                            error = res.message ?: "Unknown Error"
                        )
                    }

                    is AppResult.Done -> {
                        res.data?.let { newMovies ->
                            _state.update {
                                it.copy(
                                    moviesPage = _state.value.moviesPage + 1,
                                    state = ListingStatus.DONE,
                                    movies = _state.value.movies + newMovies
                                )
                            }
                        }
                    }
                }
            }
        }
    }


}