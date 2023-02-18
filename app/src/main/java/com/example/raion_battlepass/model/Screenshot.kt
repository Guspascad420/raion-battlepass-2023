package com.example.raion_battlepass.model

import kotlinx.serialization.Serializable

@Serializable
data class Screenshot(
    val id: Int,
    val image: String
)