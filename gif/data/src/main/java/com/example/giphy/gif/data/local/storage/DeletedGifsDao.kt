package com.example.giphy.gif.data.local.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.giphy.gif.data.local.models.DeletedGifLocalModel

@Dao
interface DeletedGifsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(obj: DeletedGifLocalModel)

    @Query("SELECT * from DELETED_GIFS")
    fun getAll(): List<DeletedGifLocalModel>
}
