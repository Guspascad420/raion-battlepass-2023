package com.example.raion_battlepass.model

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id: Int,
    val title: String,
    val thumbnail: String,
    val short_description: String,
    val genre: String,
    val platform: String,
    val publisher: String
)