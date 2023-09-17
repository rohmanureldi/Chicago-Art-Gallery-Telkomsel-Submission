package com.eldirohmanur.chicagoartgallery.presentation.state

sealed class MainUiAction {
    object OnListEnd: MainUiAction()
    object OnRetryClick: MainUiAction()
    data class OnArtworkClicked(val id: Long): MainUiAction()
    data class OnQueryChange(val query: String): MainUiAction()
}
