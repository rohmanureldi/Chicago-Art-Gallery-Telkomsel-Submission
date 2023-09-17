package com.eldirohmanur.chicagoartgallery.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eldirohmanur.chicagoartgallery.core.utils.OnBottomReached
import com.eldirohmanur.chicagoartgallery.domain.model.ArtworkDTO
import com.eldirohmanur.chicagoartgallery.presentation.components.EmptyLayout
import com.eldirohmanur.chicagoartgallery.presentation.components.ErrorLayout
import com.eldirohmanur.chicagoartgallery.presentation.components.GalleryItem
import com.eldirohmanur.chicagoartgallery.presentation.components.LoadingScreen
import com.eldirohmanur.chicagoartgallery.presentation.components.SearchBar
import com.eldirohmanur.chicagoartgallery.presentation.state.MainUiAction
import com.eldirohmanur.chicagoartgallery.presentation.viewmodel.MainActivityViewModel
import com.eldirohmanur.chicagoartgallery.ui.theme.ChicagoArtGalleryTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChicagoArtGalleryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val vm: MainActivityViewModel = koinViewModel()
                    val state = vm.state
                    Scaffold(
                        topBar = {
                            SearchBar(query = state.query, onAction = vm::onAction)
                        },
                        content = { paddingValues ->
                            val listState = rememberLazyStaggeredGridState()
                            AnimatedContent(
                                modifier = Modifier
                                    .padding(
                                        top = paddingValues.calculateTopPadding(),
                                        bottom = paddingValues.calculateBottomPadding()
                                    ),
                                targetState = state,
                                label = "initialLoading"
                            ) {
                                when {
                                    it.isEmptySearch -> {
                                        EmptyLayout()
                                    }

                                    it.isInitialLoading -> {
                                        LoadingScreen(Modifier.fillMaxSize())
                                    }

                                    it.isInitialLoadingError -> {
                                        ErrorLayout(modifier = Modifier.fillMaxSize()) {
                                            vm.onAction(MainUiAction.OnRetryClick)
                                        }
                                    }

                                    it.isPaginationLoadingError -> {
                                        ErrorLayout(modifier = Modifier.fillMaxWidth()) {
                                            vm.onAction(MainUiAction.OnRetryClick)
                                        }
                                    }

                                    else -> {
                                        LazyVerticalStaggeredGrid(
                                            modifier = Modifier
                                                .fillMaxSize(),
                                            columns = StaggeredGridCells.Fixed(2),
                                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                                            verticalItemSpacing = 12.dp,
                                            contentPadding = PaddingValues(
                                                bottom = 32.dp,
                                                start = 16.dp,
                                                end = 16.dp,
                                                top = 16.dp
                                            ),
                                            state = listState
                                        ) {
                                            itemsIndexed(
                                                items = state.artworks,
                                                key = { _: Int, item: ArtworkDTO -> item.id })
                                            { _: Int, item: ArtworkDTO ->
                                                GalleryItem(item)
                                            }

                                            item(span = StaggeredGridItemSpan.FullLine) {
                                                LoadingScreen(
                                                    Modifier
                                                        .fillMaxWidth()
                                                        .padding(vertical = 18.dp)
                                                )
                                            }
                                        }

                                        listState.OnBottomReached {
                                            vm.onAction(MainUiAction.OnListEnd)
                                        }
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
