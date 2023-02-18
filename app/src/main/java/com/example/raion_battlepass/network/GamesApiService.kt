package com.example.raion_battlepass.network

import com.example.raion_battlepass.model.Game
import com.example.raion_battlepass.model.GameDetails
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesApiService {

    @GET("api/game")
    suspend fun getGameDetails(
        @Query("id") id: Int
    ) : GameDetails

    @GET("api/games")
    suspend fun getAllGames(): List<Game>

    @GET("api/games")
    suspend fun getGames(
        @Query("category") category: String,
        @Query("platform") platform: String
    ): List<Game>

}