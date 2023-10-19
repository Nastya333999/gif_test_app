package com.example.giphy.gif.di

import com.example.giphy.gif.data.remote.api.RetrofitInitializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkHiltModule {
    @Singleton
    @Provides
    fun provideGeneralRetrofitClient(): Retrofit = RetrofitInitializer.getInstance()
}
