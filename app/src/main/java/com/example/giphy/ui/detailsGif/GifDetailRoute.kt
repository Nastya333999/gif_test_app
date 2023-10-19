package com.example.giphy.ui.detailsGif

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.giphy.ui.detailsGif.components.GifDetailItem

@Composable
fun GifDetailRoute(
    viewModel: GifDetailViewModel
) {
    val uiState by viewModel.uiGifState.collectAsState()

    SelectedGifScreen(
        uiState = uiState
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SelectedGifScreen(
    uiState: GifDetailState
) {
    val gifs = uiState.gifFlow.collectAsLazyPagingItems()
    if (gifs.itemCount > 0) {
        val pagerState = rememberPagerState(
            initialPage = uiState.initialPage,
            pageCount = { gifs.itemCount }
        )
        Column(modifier = Modifier.background(Color.Blue)) {
            HorizontalPager(
                state = pagerState
            ) {
                gifs[it]?.let { gif ->
                    GifDetailItem(gif)
                }
            }
        }
    }
}
