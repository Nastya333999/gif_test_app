package com.example.giphy.gif.data.remote.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.giphy.gif.data.remote.models.GifRemoteModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class GifRemotePagingSourceImpl @AssistedInject constructor(
    private val remoteDataSource: GifRemoteDataSource,
    @Assisted private val searchQuery: String
) : PagingSource<Int, GifRemoteModel>() {

    @AssistedFactory
    interface Factory {
        fun create(searchQuery: String): GifRemotePagingSourceImpl
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifRemoteModel> {
        return try {
            val page = params.key ?: 0
            val response = remoteDataSource.getGifList(page, pageSize = 10, searchQuery)
            val items = response.body()

            if (items != null) {
                val prevKey = if (page == 0) null else page - 1
                val nextKey = if (items.gif.size != 10) null else page + 1
                LoadResult.Page(data = items.gif, prevKey = prevKey, nextKey = nextKey)
            } else {
                LoadResult.Error(Exception("Response body is null"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifRemoteModel>): Int? = null
}