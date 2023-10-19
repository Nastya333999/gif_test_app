package com.example.giphy.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphy.gif.domain.usecase.SetIsInternetAvailableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val setIsInternetAvailableUseCase: SetIsInternetAvailableUseCase,
) : ViewModel() {
    fun setInternetAvailable(isInternetAvailable: Boolean) {
        viewModelScope.launch() {
            setIsInternetAvailableUseCase(isInternetAvailable)
        }
    }
}
