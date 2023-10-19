package com.example.giphy.ui.main

import com.example.giphy.ui.detailsGif.GifDetailsNavArgs


enum class RootDestination(val route: String) {
    HOME("home")
}

enum class HomeDestination(val route: String) {
    SEARCH("search"),
    DETAIL("detail/{${GifDetailsNavArgs.detailArg}}")
}