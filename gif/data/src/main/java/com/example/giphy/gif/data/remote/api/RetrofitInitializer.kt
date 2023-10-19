package com.example.giphy.gif.data.remote.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Retrofit

object RetrofitInitializer {

    val baseUrl = "https://api.giphy.com"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(createKotlinJsonConvertor())
            .build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    fun createKotlinJsonConvertor(): Converter.Factory {
        val json = Json {
            explicitNulls = false
            ignoreUnknownKeys = true
        }
        return json.asConverterFactory("application/json".toMediaType())
    }

}