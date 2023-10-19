package com.example.giphy.gif.domain

import androidx.paging.PagingData
import com.example.giphy.gif.domain.models.GifDomainModel
import kotlinx.coroutines.flow.Flow

interface GifRepository {
    suspend fun getGifs(searchQuery: String): Flow<PagingData<GifDomainModel>>
    suspend fun deleteGif(gifId: String)
}