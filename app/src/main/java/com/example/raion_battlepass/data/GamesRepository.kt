package com.example.raion_battlepass.data

import com.example.raion_battlepass.model.FavGame
import com.example.raion_battlepass.model.Game
import com.example.raion_battlepass.model.GameDetails
import com.example.raion_battlepass.network.GamesApiService
import kotlinx.coroutines.flow.Flow

interface GamesRepository {
    suspend fun getGames(category: String, platform: String): List<Game>
    suspend fun getAllGames(): List<Game>
    suspend fun getGameDetails(id: Int): GameDetails
    fun getAllFavGamesStream(): Flow<List<FavGame>>
    suspend fun insertFavGame(game: FavGame)
    suspend fun deleteFavGame(game: FavGame)
    suspend fun getFavGame(title: String): Flow<FavGame?>
}

class DefaultGamesRepository(
    private val gamesApiService: GamesApiService,
    private val gameDao: GameDao
) : GamesRepository {
    override suspend fun getGames(category: String, platform: String): List<Game> {
        return gamesApiService.getGames(category, platform)
    }
    override suspend fun getAllGames(): List<Game> {
        return gamesApiService.getAllGames()
    }
    override suspend fun getGameDetails(id: Int): GameDetails {
        return gamesApiService.getGameDetails(id)
    }
    override fun getAllFavGamesStream(): Flow<List<FavGame>> = gameDao.getAllFavGames()
    override suspend fun insertFavGame(game: FavGame) = gameDao.insert(game)
    override suspend fun deleteFavGame(game: FavGame) = gameDao.delete(game)
    override suspend fun getFavGame(title: String) = gameDao.getFavGame(title)
}