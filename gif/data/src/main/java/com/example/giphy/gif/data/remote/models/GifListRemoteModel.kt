package com.example.giphy.gif.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GifListRemoteModel(
    @SerialName("data")
    val gif: List<GifRemoteModel>
)
