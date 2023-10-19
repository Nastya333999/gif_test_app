package com.example.giphy.gif.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "DELETED_GIFS",
    primaryKeys = ["GIF_ID"]
)
data class DeletedGifLocalModel(
    @ColumnInfo("GIF_ID")
    val id: String,
)
