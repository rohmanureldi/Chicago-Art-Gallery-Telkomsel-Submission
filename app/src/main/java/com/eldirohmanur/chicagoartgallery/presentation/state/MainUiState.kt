package com.eldirohmanur.chicagoartgallery.presentation.state

import com.eldirohmanur.chicagoartgallery.domain.model.ArtworkDTO

data class MainUiState(
    val isInitialLoading: Boolean = false,
    val isPaginationLoading: Boolean = false,
    val artworks: List<ArtworkDTO> = listOf(),
    val query: String = "",
    val currentPage: String = "",
    val isEndReach: Boolean = false,
    val isInitialLoadingError: Boolean = false,
    val isPaginationLoadingError: Boolean = false,
    val isEmptySearch: Boolean = false
)
