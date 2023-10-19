package com.example.giphy.gif.data.local.source

import androidx.paging.PagingSource
import com.example.giphy.gif.data.local.models.DeletedGifLocalModel
import com.example.giphy.gif.data.local.models.GifLocalModel
import com.example.giphy.gif.data.local.models.GifRemotePageKeyLocalModel
import com.example.giphy.gif.data.local.storage.DeletedGifsDao
import com.example.giphy.gif.data.local.storage.GifsDao
import com.example.giphy.gif.data.local.storage.GifRemotePageKeysDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GifLocalDataSourceImpl @Inject constructor(
    private val gifsDao: GifsDao,
    private val gifRemotePageKeysDao: GifRemotePageKeysDao,
    private val deletedGifsDao: DeletedGifsDao
) : GifLocalDataSource {

    override suspend fun getPageKeyForId(id: String): GifRemotePageKeyLocalModel? {
        return gifRemotePageKeysDao.getRemotePageKeyForId(id)
    }

    override suspend fun insertRefreshPage(gifs: List<GifLocalModel>, keys: List<GifRemotePageKeyLocalModel>) {
        gifsDao.insertRefreshPage(
            gifs = gifs,
            replaceRemotePageKeys = {
                gifRemotePageKeysDao.deletePageKeys()
                gifRemotePageKeysDao.insert(keys)
            }
        )
    }

    override suspend fun insertPage(gifs: List<GifLocalModel>, keys: List<GifRemotePageKeyLocalModel>) {
        gifsDao.insertPage(
            gifs = gifs,
            insertRemotePageKeys = {
                gifRemotePageKeysDao.upsertAll(keys)
            }
        )
    }

    override fun getGifsPagingSource(searchQuery: String): PagingSource<Int, GifLocalModel> {
        return gifsDao.getGifsPagingSource(searchQuery)
    }

    override suspend fun getAllDeletedGifIds() = withContext(Dispatchers.IO) {
        deletedGifsDao.getAll().map { it.id }
    }

    override suspend fun saveDeletedGifId(id: String) = withContext(Dispatchers.IO) {
        deletedGifsDao.insert(DeletedGifLocalModel(id))
    }

    override suspend fun deleteCachedGifById(gifId: String)= withContext(Dispatchers.IO) {
        gifsDao.delete(id = gifId)
    }
}