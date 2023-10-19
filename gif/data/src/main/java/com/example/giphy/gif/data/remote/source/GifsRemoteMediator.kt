package com.example.giphy.gif.data.remote.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.giphy.gif.data.local.models.GifLocalModel
import com.example.giphy.gif.data.local.models.GifRemotePageKeyLocalModel
import com.example.giphy.gif.data.local.models.toLocalModel
import com.example.giphy.gif.data.local.source.GifLocalDataSource
import com.example.giphy.gif.data.remote.models.GifListRemoteModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.Response

@OptIn(ExperimentalPagingApi::class)
class GifsRemoteMediator @AssistedInject constructor(
    private val remoteDataSource: GifRemoteDataSource,
    private val localDataSource: GifLocalDataSource,
    @Assisted("searchQuery") private val searchQuery: String
) : RemoteMediator<Int, GifLocalModel>() {
    @AssistedFactory
    interface Factory {
        fun create(@Assisted("searchQuery") searchQuery: String): GifsRemoteMediator
    }

    private suspend fun getRemoteKeyById(id: String): GifRemotePageKeyLocalModel? {
        return localDataSource.getPageKeyForId(id)
    }

    @ExperimentalPagingApi
    private suspend fun loadPage(
        offset: Int,
        previousPage: Int?,
        nextPage: (isLoadedPageEmpty: Boolean) -> Int?,
        loadType: LoadType,
        pageSize: Int
    ): MediatorResult {
        val apiResponse: Response<GifListRemoteModel>? = try {
            remoteDataSource.getGifList(
                offset = offset,
                pageSize = pageSize,
                searchQuery = searchQuery
            )
        }catch (_: Exception){
            return MediatorResult.Success(true)
        }

        return if (apiResponse?.isSuccessful == true) {
            val apiResponseBody = apiResponse.body() ?: return MediatorResult.Success(false)

            val gifs = apiResponseBody.gif.mapIndexed { index, country ->
                country.toLocalModel(searchQuery, offset, index)
            }

            val remotePageKeys = apiResponseBody.gif.map { gif ->
                GifRemotePageKeyLocalModel(
                    pagingItemId = gif.id,
                    offset = offset,
                    prevPage = previousPage,
                    nextPage = nextPage(apiResponseBody.gif.isEmpty())
                )
            }

            if (loadType == LoadType.REFRESH) {
                localDataSource.insertRefreshPage(
                    gifs = gifs,
                    keys = remotePageKeys
                )
            } else {
                localDataSource.insertPage(
                    gifs = gifs,
                    keys = remotePageKeys
                )
            }
            MediatorResult.Success(apiResponseBody.gif.isEmpty())
        } else {
            MediatorResult.Error(Exception(apiResponse?.message()))
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, GifLocalModel>): MediatorResult {
        val offset: Int = when (loadType) {
            LoadType.REFRESH -> {
                getRemoteKeyClosestToCurrentPosition(state)?.nextPage?.minus(10)
                    ?: INITIAL_OFFSET
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                remoteKeys?.prevPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        return loadPage(
            offset = offset,
            previousPage = if (offset >= 10) offset - 10 else null,
            nextPage = { isEmpty ->
                if (isEmpty) null else offset + 10
            },
            loadType = loadType,
            pageSize = state.config.pageSize
        )
    }


    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, GifLocalModel>): GifRemotePageKeyLocalModel? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { localPagingItem ->
            getRemoteKeyById(localPagingItem.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, GifLocalModel>): GifRemotePageKeyLocalModel? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { localPagingItem ->
            getRemoteKeyById(localPagingItem.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, GifLocalModel>): GifRemotePageKeyLocalModel? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { localPagingItemId ->
                getRemoteKeyById(localPagingItemId)
            }
        }
    }


    companion object {
        private const val INITIAL_OFFSET = 0
    }
}
