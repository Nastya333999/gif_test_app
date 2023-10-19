package com.example.giphy.gif.di

import android.content.Context
import com.example.giphy.gif.data.local.source.GifLocalDataSource
import com.example.giphy.gif.data.local.source.GifLocalDataSourceImpl
import com.example.giphy.gif.data.local.storage.AppDatabase
import com.example.giphy.gif.data.local.storage.DeletedGifsDao
import com.example.giphy.gif.data.local.storage.GifRemotePageKeysDao
import com.example.giphy.gif.data.local.storage.GifsDao
import com.example.giphy.gif.data.remote.api.GifApi
import com.example.giphy.gif.data.remote.api.RetrofitInitializer
import com.example.giphy.gif.data.remote.source.GifRemoteDataSource
import com.example.giphy.gif.data.remote.source.GifRemoteDataSourceImpl
import com.example.giphy.gif.data.repository.GifRepositoryImpl
import com.example.giphy.gif.domain.GifRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface GifsHiltModule {

    @Binds
    fun bindGifsRemoteDataSource(
        gifRemoteDataSourceImpl: GifRemoteDataSourceImpl
    ): GifRemoteDataSource

    @Binds
    fun bindGifsLocalDataSource(
        gifLocalDataSourceImpl: GifLocalDataSourceImpl
    ): GifLocalDataSource

    @Binds
    fun bindGifsRepository(
        gifRepositoryImpl: GifRepositoryImpl
    ): GifRepository
    @Module
    @InstallIn(SingletonComponent::class)
    object GifsHiltModuleDependency{
        @Provides
        fun provideGifsDao(@ApplicationContext context: Context): @JvmSuppressWildcards GifsDao = AppDatabase.getInstance(context).getGifsDao()

        @Provides
        fun provideDeletedGifsDao(@ApplicationContext context: Context): @JvmSuppressWildcards DeletedGifsDao = AppDatabase.getInstance(context).getDeletedGifsDao()

        @Provides
        fun provideGifRemotePageKeysDao(@ApplicationContext context: Context): @JvmSuppressWildcards GifRemotePageKeysDao = AppDatabase.getInstance(context).getGifRemotePageKeysDao()

        @Provides
        fun provideGifApi(): GifApi = RetrofitInitializer.getInstance().create(GifApi::class.java)

    }
}