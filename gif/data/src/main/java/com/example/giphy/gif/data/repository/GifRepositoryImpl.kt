package com.example.giphy.gif.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.example.giphy.gif.data.local.models.toDomainModel
import com.example.giphy.gif.data.local.source.GifLocalDataSource
import com.example.giphy.gif.data.remote.source.GifsRemoteMediator
import com.example.giphy.gif.domain.GifRepository
import com.example.giphy.gif.domain.models.GifDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Provider

class GifRepositoryImpl @Inject constructor(
    private val remoteMediator: Provider<GifsRemoteMediator.Factory>,
    private val localDataSource: GifLocalDataSource
) : GifRepository {
    private val pagingConfig = PagingConfig(
        pageSize = 10,
        initialLoadSize = 10
    )

    override suspend fun deleteGif(gifId: String) {
        localDataSource.saveDeletedGifId(id = gifId)
        localDataSource.deleteCachedGifById(gifId)
    }

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getGifs(searchQuery: String): Flow<PagingData<GifDomainModel>> {
        return Pager(
            config = pagingConfig,
            remoteMediator = remoteMediator.get().create(searchQuery)
        ) {
            localDataSource.getGifsPagingSource(searchQuery)
        }.flow
            .map { pagingData ->
                pagingData
                    .filter { !localDataSource.getAllDeletedGifIds().contains(it.id) }
                    .map { pagingItem -> pagingItem.toDomainModel() }
            }
    }
}
