package com.example.giphy.gif.data.remote.source

import com.example.giphy.gif.data.remote.api.GifApi
import com.example.giphy.gif.data.remote.models.GifListRemoteModel
import retrofit2.Response
import javax.inject.Inject

class GifRemoteDataSourceImpl @Inject constructor(
    private val gifApi: GifApi
) : GifRemoteDataSource {

    override suspend fun getGifList(offset: Int, pageSize: Int, searchQuery: String): Response<GifListRemoteModel> {

        val response = gifApi.getGif(offset = offset, pageSize = pageSize, searchQuery = searchQuery)

        return if (response.isSuccessful) {
            val gifListRemoteModel = response.body()
            Response.success(gifListRemoteModel)
        } else {
            Response.error(response.code(), response.errorBody()!!)
        }
    }
}