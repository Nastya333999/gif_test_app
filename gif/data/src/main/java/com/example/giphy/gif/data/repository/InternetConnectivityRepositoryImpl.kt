package com.example.giphy.gif.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class InternetConnectivityRepositoryImpl @Inject constructor() : com.example.giphy.gif.domain.InternetConnectivityRepository {
    private val _isInternetAvailable = MutableStateFlow(false)
    override val isInternetAvailable: Flow<Boolean> = _isInternetAvailable

    override suspend fun setIsInternetAvailable(isInternetAvailable: Boolean) {
        _isInternetAvailable.emit(isInternetAvailable)
    }
}