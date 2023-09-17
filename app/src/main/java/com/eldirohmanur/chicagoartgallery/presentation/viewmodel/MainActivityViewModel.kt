package com.eldirohmanur.chicagoartgallery.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eldirohmanur.chicagoartgallery.core.domain.Resource
import com.eldirohmanur.chicagoartgallery.domain.model.ArtworkDTO
import com.eldirohmanur.chicagoartgallery.domain.usecase.ArtworkUseCase
import com.eldirohmanur.chicagoartgallery.presentation.state.MainUiAction
import com.eldirohmanur.chicagoartgallery.presentation.state.MainUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val useCase: ArtworkUseCase
) : ViewModel() {
    var state by mutableStateOf(MainUiState())
        private set

    private var currentPage: Int = 1
    private var isLoading: Boolean = false

    private var queryJob: Job? = null
    private var mainArtworks = listOf<ArtworkDTO>()

    init {
        fetchArtwork()
    }

    fun onAction(action: MainUiAction) {
        when (action) {
            is MainUiAction.OnArtworkClicked -> {
                /** Handle later or maybe not, its not in the requirement **/
            }

            is MainUiAction.OnQueryChange -> onQueryChange(action.query)
            MainUiAction.OnListEnd -> fetchArtwork()
            MainUiAction.OnRetryClick -> fetchArtwork()
        }
    }

    private fun onQueryChange(query: String) {
        state = state.copy(
            query = query
        )

        viewModelScope.launch {
            queryJob?.cancelAndJoin()
        }

        if (query.isEmpty()) {
            state = state.copy(
                artworks = mainArtworks,
                isInitialLoading = false,
                isEmptySearch = false,
                isInitialLoadingError = false
            )
        } else {
            state = state.copy(
                isInitialLoading = true
            )
            queryJob = viewModelScope.launch {
                delay(250L)
                searchArtwork(query)
            }

            queryJob?.invokeOnCompletion {
                when (it) {
                    is CancellationException -> {
                        if (state.isInitialLoading) {
                            state = state.copy(
                                isInitialLoading = query.isNotEmpty(),
                                isInitialLoadingError = false
                            )
                        }
                    }
                    null -> {
                        state = state.copy(
                            isInitialLoading = false,
                            isInitialLoadingError = false
                        )
                    }
                    else -> {
                        state = state.copy(
                            isInitialLoading = false,
                            isInitialLoadingError = true
                        )
                    }
                }
            }
        }
    }

    private fun fetchArtwork() {
        if (isLoading || state.query.isNotEmpty()) return

        isLoading = true
        viewModelScope.launch {
            state = state.copy(
                isInitialLoading = currentPage == 1,
                isPaginationLoading = currentPage != 1,
            )

            when (val response = useCase.getArtworks(currentPage)) {
                is Resource.Success -> {
                    mainArtworks = state.artworks + response.data.orEmpty()

                    state = state.copy(
                        isInitialLoading = false,
                        isPaginationLoading = false,
                        artworks = state.artworks + response.data.orEmpty(),
                        isEndReach = response.data.isNullOrEmpty()
                    )
                    currentPage++
                }

                is Resource.Error -> {
                    state = state.copy(
                        isInitialLoading = false,
                        isPaginationLoading = false,
                        isPaginationLoadingError = currentPage != 1,
                        isInitialLoadingError = currentPage == 1
                    )
                }
            }
            isLoading = false
        }
    }

    private suspend fun searchArtwork(query: String) {
        val response = useCase.searchArtworks(query)
        state = if (response is Resource.Success) {
            state.copy(
                artworks = response.data.orEmpty(),
                isEmptySearch = response.data.isNullOrEmpty(),
                isInitialLoadingError = false
            )
        } else {
            state.copy(
                isEmptySearch = false
            )
        }
    }
}