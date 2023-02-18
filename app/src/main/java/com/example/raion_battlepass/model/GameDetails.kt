package com.example.raion_battlepass.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetails(
    val description: String,
    val developer: String,
    @SerialName("freetogame_profile_url")
    val freetogameProfileUrl: String,
    @SerialName("game_url")
    val gameUrl: String,
    val genre: String,
    val id: Int,
    @SerialName("minimum_system_requirements")
    val minimumSystemRequirements: MinimumSystemRequirements =
        MinimumSystemRequirements(
        graphics = "",
        memory = "",
        processor = "",
        os = "",
        storage = ""
    ),
    val platform: String,
    val publisher: String,
    @SerialName("release_date")
    val releaseDate: String,
    val screenshots: List<Screenshot>,
    @SerialName("short_description")
    val shortDescription: String,
    val status: String,
    val thumbnail: String,
    val title: String
)