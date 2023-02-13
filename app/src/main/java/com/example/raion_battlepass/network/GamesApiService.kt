package com.example.raion_battlepass.network

import com.example.raion_battlepass.model.Game
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApiService {
    @GET("game")
    suspend fun getGames(
        @Query("category") category: String,
        @Query("platform") platform: String
    ): List<Game>
}