package com.example.giphy.gif.domain.usecase

import com.example.giphy.gif.domain.InternetConnectivityRepository
import javax.inject.Inject

class GetIsInternetAvailableUseCase @Inject constructor(private val internetConnectivityRepository: InternetConnectivityRepository) {
    operator fun invoke() = internetConnectivityRepository.isInternetAvailable
}