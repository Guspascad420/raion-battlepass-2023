package com.example.raion_battlepass.model

import kotlinx.serialization.Serializable

@Serializable
data class MinimumSystemRequirements(
    val graphics: String,
    val memory: String,
    val os: String,
    val processor: String,
    val storage: String
)