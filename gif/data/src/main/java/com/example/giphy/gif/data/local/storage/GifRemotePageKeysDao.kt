package com.example.giphy.gif.data.local.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.giphy.gif.data.local.models.GifRemotePageKeyLocalModel

@Dao
interface GifRemotePageKeysDao {
    @Query("SELECT * FROM GIF_REMOTE_PAGE_KEYS WHERE :id = GIF_REMOTE_PAGE_ITEM_ID")
    suspend fun getRemotePageKeyForId(id: String): GifRemotePageKeyLocalModel?

    @Query("DELETE FROM GIF_REMOTE_PAGE_KEYS")
    suspend fun deletePageKeys()

    @Upsert
    suspend fun upsertAll(keys: List<GifRemotePageKeyLocalModel>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(objects: List<GifRemotePageKeyLocalModel>): List<Long>
}
