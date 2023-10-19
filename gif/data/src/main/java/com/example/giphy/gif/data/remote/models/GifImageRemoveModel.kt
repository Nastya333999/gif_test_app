package com.example.giphy.gif.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GifImageRemoveModel(
    @SerialName("url")
    val url: String
)
