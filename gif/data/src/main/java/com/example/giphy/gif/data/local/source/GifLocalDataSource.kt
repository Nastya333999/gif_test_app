package com.example.giphy.gif.data.local.source

import androidx.paging.PagingSource
import com.example.giphy.gif.data.local.models.GifLocalModel
import com.example.giphy.gif.data.local.models.GifRemotePageKeyLocalModel

interface GifLocalDataSource {
    suspend fun getPageKeyForId(id: String): GifRemotePageKeyLocalModel?
    suspend fun insertRefreshPage(gifs: List<GifLocalModel>, keys: List<GifRemotePageKeyLocalModel>)
    suspend fun insertPage(gifs: List<GifLocalModel>, keys: List<GifRemotePageKeyLocalModel>)
    fun getGifsPagingSource(searchQuery: String): PagingSource<Int, GifLocalModel>
    suspend fun saveDeletedGifId(id: String)
    suspend fun getAllDeletedGifIds(): List<String>
    suspend fun deleteCachedGifById(gifId: String)
}
