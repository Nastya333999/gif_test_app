package com.example.giphy.gif.data.remote.api

import com.example.giphy.gif.data.remote.models.GifListRemoteModel
import retrofit2.Response
import retrofit2.http.*

interface GifApi {
    @GET("v1/gifs/search?api_key=SxxcNLzUl1nQDezP4dV1LPUCMih7lzWL")
    suspend fun getGif(
        @Query("q") searchQuery: String,
        @Query("offset") offset: Int,
        @Query("limit") pageSize: Int,
    ): Response<GifListRemoteModel>
}
