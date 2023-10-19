package com.example.giphy.ui.detailsGif

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.giphy.core.extensions.fromNavArgString
import com.example.giphy.gif.domain.usecase.GetGifsUseCase
import com.example.giphy.ui.searchGif.GifPresentationModel
import com.example.giphy.ui.searchGif.toPresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifDetailViewModel @Inject constructor(
    private val getGifsUseCase: GetGifsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        GifDetailState(
            gifFlow = flowOf(),
            initialPage = 0
        ),
    )
    val uiGifState: StateFlow<GifDetailState> = _uiState

    private val gifDetailArgs = savedStateHandle.get<String>(GifDetailsNavArgs.detailArg)
        ?.fromNavArgString<GifDetailArgs>()

    init {
        gifDetailArgs?.let {
            loadGifs(it.searchQuery)
        }
    }

    private fun loadGifs(searchQuery: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    gifFlow = getGifsUseCase(searchQuery).map { it.map { it.toPresentationModel() } },
                    initialPage = gifDetailArgs?.itemIndex ?: 0
                )
            }
        }
    }

}

data class GifDetailState(
    val gifFlow: Flow<PagingData<GifPresentationModel>>,
    val initialPage: Int
)