package com.example.giphy.ui.searchGif

import com.example.giphy.gif.domain.models.GifDomainModel

data class GifPresentationModel(
    val id: String,
    val title: String,
    val url: String
)

fun GifDomainModel.toPresentationModel(): GifPresentationModel = GifPresentationModel(
    id = this.id,
    title = this.title,
    url = this.url
)