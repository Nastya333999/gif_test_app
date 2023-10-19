package com.example.giphy.gif.data.remote.source

import com.example.giphy.gif.data.remote.models.GifListRemoteModel
import retrofit2.Response

interface GifRemoteDataSource {
    suspend fun getGifList(offset: Int, pageSize: Int, searchQuery: String): Response<GifListRemoteModel>
}