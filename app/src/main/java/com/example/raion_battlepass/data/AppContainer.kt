package com.example.raion_battlepass.data

import com.example.raion_battlepass.network.GamesApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType

interface AppContainer {
    val gamesRepository: GamesRepository
}

class DefaultAppContainer : AppContainer {

    private val BASE_URL =
        "https://www.freetogame.com/api"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: GamesApiService by lazy {
        retrofit.create(GamesApiService::class.java)
    }

    override val gamesRepository: GamesRepository by lazy {
        DefaultGamesRepository(retrofitService)
    }
}