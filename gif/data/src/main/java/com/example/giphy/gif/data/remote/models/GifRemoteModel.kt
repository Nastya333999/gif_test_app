package com.example.giphy.gif.data.remote.models

import com.example.giphy.gif.domain.models.GifDomainModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GifRemoteModel(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("images")
    val images: GifImagesRemoveModel
)

fun GifRemoteModel.toDomainModel(): GifDomainModel = GifDomainModel(
    id = this.id,
    url = this.images.downsizedLarge.url,
    title = this.title
)