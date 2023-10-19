package com.example.giphy.gif.data.local.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.giphy.gif.data.local.models.DeletedGifLocalModel
import com.example.giphy.gif.data.local.models.GifLocalModel
import com.example.giphy.gif.data.local.models.GifRemotePageKeyLocalModel

@Database(
    entities = [
        GifLocalModel::class,
        GifRemotePageKeyLocalModel::class,
        DeletedGifLocalModel::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getGifsDao(): GifsDao
    abstract fun getGifRemotePageKeysDao(): GifRemotePageKeysDao
    abstract fun getDeletedGifsDao(): DeletedGifsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "appDatabase.db"
            ).build()

            INSTANCE = instance
            return instance
        }
    }
}
