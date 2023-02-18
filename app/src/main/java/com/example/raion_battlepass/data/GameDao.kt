package com.example.raion_battlepass.data

import androidx.room.*
import com.example.raion_battlepass.model.FavGame
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM fav_games ORDER BY title")
    fun getAllFavGames(): Flow<List<FavGame>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: FavGame)

    @Query("SELECT * FROM fav_games WHERE title = :title")
    fun getFavGame(title: String): Flow<FavGame?>

    @Delete
    suspend fun delete(game: FavGame)
}