package com.example.giphy.gif.data.local.storage

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.giphy.gif.data.local.models.GifLocalModel

@Dao
interface GifsDao {

    @Upsert
    suspend fun upsertGifs(gifs: List<GifLocalModel>)

    @Query("DELETE FROM GIFS")
    suspend fun deleteGifs()

    @Transaction
    @Query("SELECT * FROM GIFS WHERE :searchQuery = GIF_SEARCH_QUERY ORDER BY GIF_OFFSET ASC, GIF_PAGE_INDEX ASC")
    fun getGifsPagingSource(searchQuery: String): PagingSource<Int, GifLocalModel>

    @Transaction
    suspend fun insertRefreshPage(
        gifs: List<GifLocalModel>,
        replaceRemotePageKeys: suspend () -> Unit
    ) {
        deleteGifs()
        upsertGifs(gifs)
        replaceRemotePageKeys()
    }

    @Transaction
    suspend fun insertPage(
        gifs: List<GifLocalModel>,
        insertRemotePageKeys: suspend () -> Unit
    ) {
        upsertGifs(gifs)
        insertRemotePageKeys()
    }

    @Query("DELETE FROM GIFS WHERE GIF_ID = :id")
    fun delete(id: String)
}
