package com.example.giphy.ui.searchGif

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.giphy.R
import com.example.giphy.ui.res.Dimens
import com.example.giphy.ui.res.GifColors
import com.example.giphy.ui.res.background
import com.example.giphy.ui.res.primaryOriginal
import com.example.giphy.ui.searchGif.components.items.GifItem
import com.example.giphy.ui.searchGif.components.GifPlaceholder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
 fun GifRoute(
    viewModel: GifViewModel,
    navigateToDetail: (searchQuery: String, itemIndex: Int) -> Unit
) {
    val uiChooseCityState by viewModel.uiGifState.collectAsState()
    var isNeedToShowConfirmDeleteDialog by remember { mutableStateOf(false) }

    GifScreen(
        uiState = uiChooseCityState,
        setFilterText = viewModel::setSearchTextFilter,
        findGifs = viewModel::findGif,
        onDeleteItem = viewModel::deleteItem,
        isNeedToShowConfirmDeleteDialog = isNeedToShowConfirmDeleteDialog,
        onDismissConfirmDeleteDialog = { isNeedToShowConfirmDeleteDialog = false },
        onDeleteItemConfirmed = viewModel::deleteItemConfirmed,
        onGifClick = navigateToDetail
    )

    HandleSideEffects(
        sideEffect = viewModel.sideEffect,
        clearSideEffectData = viewModel::clearSideEffect,
        showConfirmationDialog = { isNeedToShowConfirmDeleteDialog = !isNeedToShowConfirmDeleteDialog }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun GifScreen(
    uiState: GifState,
    setFilterText: (filterText: String) -> Unit,
    findGifs: () -> Unit,
    onDeleteItem: (GifPresentationModel) -> Unit,
    onDeleteItemConfirmed: () -> Unit,
    onDismissConfirmDeleteDialog: () -> Unit,
    isNeedToShowConfirmDeleteDialog: Boolean,
    onGifClick: (searchQuery: String, itemIndex: Int) -> Unit
) {
    val gifs = uiState.gifFlow.collectAsLazyPagingItems()
    val keyBoardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .background(GifColors.background())
            .padding(horizontal = Dimens.spacingNormal)
    ) {
        com.example.giphy.ui.components.Toolbar(
            title = stringResource(id = R.string.gif_title),
        )

        if (isNeedToShowConfirmDeleteDialog) {
            com.example.giphy.ui.components.DeleteGifDialog(
                onDismiss = onDismissConfirmDeleteDialog,
                onConfirm = {
                    onDeleteItemConfirmed()
                    onDismissConfirmDeleteDialog()
                }
            )
        }

        com.example.giphy.ui.components.SearchView(
            modifier = Modifier,
            placeholder = stringResource(id = R.string.placeholder_text),
            contentPadding = PaddingValues(
                start = Dimens.spacingNormal,
                top = Dimens.spacingNormalSpecial,
                bottom = Dimens.spacingNormalSpecial,
            ),
            searchFieldValue = uiState.filterText,
            setFilterText = setFilterText,
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyBoardController?.hide()
                    findGifs()
                }
            )
        )

        val isInitError by remember(gifs) {
            derivedStateOf { gifs.loadState.refresh is LoadState.Error }
        }
        val isInitLoading by remember(gifs) {
            derivedStateOf { gifs.loadState.refresh is LoadState.Loading }
        }
        val isAppendLoading by remember(gifs) {
            derivedStateOf { gifs.loadState.append is LoadState.Loading }
        }
        val isAppendError by remember(gifs) {
            derivedStateOf { gifs.loadState.append is LoadState.Error }
        }
        val isNoItems by remember(gifs) {
            derivedStateOf {
                gifs.loadState.refresh is LoadState.NotLoading &&
                        gifs.loadState.append.endOfPaginationReached &&
                        gifs.itemSnapshotList.isEmpty()
            }
        }

        when {
            uiState.filterText.isEmpty() -> {
                Spacer(modifier = Modifier.weight(1f))

                com.example.giphy.ui.components.EmptyView(
                    title = stringResource(id = R.string.empty_view_title),
                    message = stringResource(id = R.string.start_typing_search)
                )

                Spacer(modifier = Modifier.weight(2f))
            }

            isInitError -> {
                com.example.giphy.ui.components.ErrorView(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = Dimens.spacingNormal),
                    onReload = { gifs.retry() }
                )
            }
            uiState.isOffline && isNoItems && uiState.filterText.isNotEmpty()-> {
                com.example.giphy.ui.components.OfflineView(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = Dimens.spacingNormal)
                )
            }

            isNoItems -> {
                Spacer(modifier = Modifier.weight(1f))

                com.example.giphy.ui.components.EmptyView(
                    title = stringResource(id = R.string.empty_view_title),
                    message = stringResource(id = R.string.no_search_results)
                )

                Spacer(modifier = Modifier.weight(2f))
            }

            else -> {
                if (uiState.isOffline){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = stringResource(id = R.string.in_offline),
                        textAlign = TextAlign.Center
                    )
                }
                val lazyListState = rememberLazyListState()
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(Dimens.spacingBig),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(Dimens.spacingNormal),
                    state = if (isInitLoading) rememberLazyListState() else lazyListState
                ) {
                    if (isInitLoading && gifs.itemSnapshotList.isEmpty()) {
                        items(3) {
                            GifPlaceholder()
                        }
                    } else {
                        items(count = gifs.itemCount) { index ->
                            when (val item = gifs[index]) {
                                null -> {
                                    GifPlaceholder()
                                }

                                is GifPresentationModel -> {
                                    GifItem(
                                        item = item,
                                        onDeleteItem = onDeleteItem,
                                        onItemClick = { onGifClick(uiState.filterText, index) }
                                    )
                                }
                            }
                        }

                        if (isAppendLoading) {
                            item {
                                CircularProgressIndicator(color = GifColors.primaryOriginal())
                            }
                        }
                    }
                }

                // Retry on error, if user scroll to end
                LaunchedEffect(
                    isAppendError,
                    lazyListState.isScrollInProgress,
                ) {
                    if (isAppendError && lazyListState.isScrollInProgress) gifs.retry()
                }
            }
        }
    }
}

@Composable
internal fun HandleSideEffects(
    sideEffect: Flow<SideEffect?>,
    clearSideEffectData: () -> Unit,
    showConfirmationDialog: (item: GifPresentationModel) -> Unit,
) {
    LaunchedEffect(Unit) {
        sideEffect.collectLatest { sideEffectState ->
            when (sideEffectState) {
                is SideEffect.ShowConfirmationDeleteDialog -> {
                    showConfirmationDialog(sideEffectState.item)
                    clearSideEffectData()
                }

                null -> {
                    // do nothing
                }
            }
        }
    }
}
