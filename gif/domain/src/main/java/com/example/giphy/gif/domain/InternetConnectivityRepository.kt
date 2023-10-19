package com.example.giphy.gif.domain

import kotlinx.coroutines.flow.Flow

interface InternetConnectivityRepository {

    val isInternetAvailable: Flow<Boolean>

    suspend fun setIsInternetAvailable(isInternetAvailable: Boolean)
}