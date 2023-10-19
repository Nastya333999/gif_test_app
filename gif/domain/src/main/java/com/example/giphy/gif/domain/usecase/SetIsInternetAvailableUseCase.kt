package com.example.giphy.gif.domain.usecase

import com.example.giphy.gif.domain.InternetConnectivityRepository
import javax.inject.Inject

class SetIsInternetAvailableUseCase @Inject constructor(private val internetConnectivityRepository: InternetConnectivityRepository) {
    suspend operator fun invoke(isInternetAvailable: Boolean) = internetConnectivityRepository.setIsInternetAvailable(isInternetAvailable)
}