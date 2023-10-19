package com.example.giphy.gif.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "GIF_REMOTE_PAGE_KEYS",
    primaryKeys = ["GIF_REMOTE_PAGE_ITEM_ID"]
)
data class GifRemotePageKeyLocalModel(
    @ColumnInfo("GIF_REMOTE_PAGE_ITEM_ID")
    val pagingItemId: String,
    @ColumnInfo("GIF_REMOTE_PAGE_CURRENT")
    val offset: Int,
    @ColumnInfo("GIF_REMOTE_PAGE_PREVIOUS")
    val prevPage: Int?,
    @ColumnInfo("GIF_REMOTE_PAGE_NEXT")
    val nextPage: Int?
)
