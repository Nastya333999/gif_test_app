package com.example.giphy.gif.di

import com.example.giphy.gif.data.repository.InternetConnectivityRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface InternetHiltModule {

    @Binds
    @Singleton
    fun bindInternetConnectivityRepository(
        internetConnectivityRepositoryImpl: InternetConnectivityRepositoryImpl
    ): com.example.giphy.gif.domain.InternetConnectivityRepository

}