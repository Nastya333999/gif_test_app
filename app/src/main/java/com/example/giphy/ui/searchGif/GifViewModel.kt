package com.example.giphy.ui.searchGif

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.giphy.gif.domain.usecase.DeleteGifUseCase
import com.example.giphy.gif.domain.usecase.GetGifsUseCase
import com.example.giphy.gif.domain.usecase.GetIsInternetAvailableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GifViewModel @Inject constructor(
    private val getGifsUseCase: GetGifsUseCase,
    private val deleteGifUseCase: DeleteGifUseCase,
    private val getIsInternetAvailableUseCase: GetIsInternetAvailableUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        GifState(
            gifFlow = flowOf(),
            filterText = "",
            isOffline = false
        )
    )
    val uiGifState: StateFlow<GifState> = _uiState

    private val _sideEffect = MutableStateFlow<SideEffect?>(null)
    val sideEffect: SharedFlow<SideEffect?> = _sideEffect

    private var itemForDelete: GifPresentationModel? = null

    init {
        setupSearch()
        loadOfflineStatus()
    }

    private fun loadGifs(searchQuery: String) {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    gifFlow = getGifsUseCase(searchQuery)
                        .map { pagingData ->
                            pagingData.map { gifDomainModel ->
                                gifDomainModel.toPresentationModel()
                            }
                        }
                )
            }
        }
    }

    private fun loadOfflineStatus(){
        viewModelScope.launch {
            getIsInternetAvailableUseCase().collectLatest { isInternetAvailable ->
                _uiState.update {state ->
                    state.copy(
                        isOffline = !isInternetAvailable
                    )
                }
            }
        }
    }

    fun setSearchTextFilter(value: String) {
        viewModelScope.launch {
            _uiState.update { state -> state.copy(filterText = value) }
        }
    }

    private fun setupSearch() {
        viewModelScope.launch {
            _uiState
                .distinctUntilChangedBy { uiState -> uiState.filterText }
                .filter { uiState -> uiState.filterText.isNotEmpty() }
                .debounce(SEARCH_DELAY)
                .collectLatest { uiState -> loadGifs(uiState.filterText) }
        }
    }

    fun findGif() {
        val typedGifName = _uiState.value.filterText
        loadGifs(typedGifName)
    }

    fun deleteItem(item: GifPresentationModel) {
        viewModelScope.launch {
            itemForDelete = item
            _sideEffect.emit(SideEffect.ShowConfirmationDeleteDialog(item))
        }
    }

    fun deleteItemConfirmed() {
        viewModelScope.launch {
            itemForDelete?.let { gifPresentationModel ->
                deleteGifUseCase(gifPresentationModel.id)
            }
        }
    }

    fun clearSideEffect() {
        viewModelScope.launch {
            _sideEffect.value = null
        }
    }

    companion object {
        const val SEARCH_DELAY = 300L
    }
}

data class GifState(
    val gifFlow: Flow<PagingData<GifPresentationModel>>,
    val filterText: String,
    val isOffline: Boolean
)

sealed class SideEffect {
    data class ShowConfirmationDeleteDialog(val item: GifPresentationModel) : SideEffect()
}
