package com.example.giphy.ui.detailsGif

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.gifDetailNavGraph(
    rootRoute: String,
) {
    composable(
        route = rootRoute,
        arguments = GifDetailsNavArgs.navArgs
    ) {
        GifDetailRoute(
            viewModel = hiltViewModel()
        )
    }
}