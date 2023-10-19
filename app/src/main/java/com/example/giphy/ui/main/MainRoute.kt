package com.example.giphy.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.giphy.ui.detailsGif.gifDetailNavGraph
import com.example.giphy.ui.searchGif.searchGifNavGraph

@Composable
internal fun MainRoute() {
    val navController = rememberNavController()

    MainScreen(
        navController = navController
    )
}

@Composable
internal fun MainScreen(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = RootDestination.HOME.route
    ) {
        navigation(
            route = RootDestination.HOME.route,
            startDestination = HomeDestination.SEARCH.route
        ) {

            searchGifNavGraph(
                rootRoute = HomeDestination.SEARCH.route,
                navigateToDetail = { searchQuery: String, itemIndex: Int ->
                    navController.navigateToGifDetails(
                        searchQuery = searchQuery,
                        itemIndex = itemIndex
                    )
                }
            )

            gifDetailNavGraph(
                rootRoute = HomeDestination.DETAIL.route
            )
        }
    }
}