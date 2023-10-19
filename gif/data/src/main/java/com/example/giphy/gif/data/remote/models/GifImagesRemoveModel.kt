package com.example.giphy.gif.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GifImagesRemoveModel(
    @SerialName("downsized_large")
    val downsizedLarge: GifImageRemoveModel
)