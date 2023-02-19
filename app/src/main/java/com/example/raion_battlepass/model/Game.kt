package com.example.raion_battlepass.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val developer: String,
    val freetogame_profile_url: String,
    val game_url: String,
    val genre: String,
    val id: Int,
    val platform: String,
    val publisher: String,
    val release_date: String,
    val short_description: String,
    val thumbnail: String,
    val title: String
)

@Entity(tableName = "fav_games")
data class FavGame(
    @PrimaryKey val id: Int,
    val title: String,
    val release_date: String,
    val platform: String,
    val publisher: String,
    val thumbnail: String
)