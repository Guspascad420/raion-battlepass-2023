package com.example.raion_battlepass.data

import com.example.raion_battlepass.model.Game
import com.example.raion_battlepass.network.GamesApiService

interface GamesRepository {
    suspend fun getGames(category: String, platform: String): List<Game>
}

class DefaultGamesRepository(private val gamesApiService: GamesApiService) : GamesRepository {
    override suspend fun getGames(category: String, platform: String): List<Game> {
        return gamesApiService.getGames(category, platform)
    }
}