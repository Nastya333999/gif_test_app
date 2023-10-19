package com.example.giphy.ui.searchGif

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.searchGifNavGraph(
    rootRoute: String,
    navigateToDetail: (searchQuery: String, itemIndex: Int) -> Unit
) {
    composable(
        route = rootRoute,
    ) {
        GifRoute(
            viewModel = hiltViewModel(),
            navigateToDetail = navigateToDetail
        )
    }
}