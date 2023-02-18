package com.example.raion_battlepass.ui.navigation

sealed class Screen(val route: String) {
    object Games: Screen("games")
}