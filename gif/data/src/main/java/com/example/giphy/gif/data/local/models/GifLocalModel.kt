package com.example.giphy.gif.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.giphy.gif.data.remote.models.GifRemoteModel
import com.example.giphy.gif.domain.models.GifDomainModel

@Entity(
    tableName = "GIFS",
    primaryKeys = ["GIF_ID"]
)
data class GifLocalModel(
    @ColumnInfo("GIF_ID")
    val id: String,
    @ColumnInfo("GIF_TITLE")
    val title: String,
    @ColumnInfo("GIF_URL")
    val url: String,
    @ColumnInfo("GIF_SEARCH_QUERY")
    val searchQuery: String,
    @ColumnInfo("GIF_OFFSET")
    val offset: Int,
    @ColumnInfo("GIF_PAGE_INDEX")
    val pageIndex: Int
)

fun GifRemoteModel.toLocalModel(
    searchQuery: String,
    offset: Int,
    index: Int
): GifLocalModel = GifLocalModel(
    id = this.id,
    title = this.title,
    url = this.images.downsizedLarge.url,
    searchQuery = searchQuery,
    offset = offset,
    pageIndex = index
)

fun GifLocalModel.toDomainModel(): GifDomainModel = GifDomainModel(
    id = this.id,
    url = this.url,
    title = this.title
)
