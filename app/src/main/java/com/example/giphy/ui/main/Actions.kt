package com.example.giphy.ui.main

import androidx.navigation.NavController
import com.example.giphy.core.extensions.toNavArgString
import com.example.giphy.ui.detailsGif.GifDetailArgs
import com.example.giphy.ui.detailsGif.GifDetailsNavArgs

fun NavController.navigateToGifDetails(searchQuery: String, itemIndex: Int) {
    val route = HomeDestination.DETAIL.route.replace("{${GifDetailsNavArgs.detailArg}}", GifDetailArgs(searchQuery, itemIndex).toNavArgString())
    navigate(route)
}