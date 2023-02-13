package com.example.raion_battlepass.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raion_battlepass.data.GamesRepository
import com.example.raion_battlepass.model.Game
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface GameUiState {
    data class Success(val results: List<Game>) : GameUiState
    object Error : GameUiState
    object Loading : GameUiState
    object Blank : GameUiState
}

class GameViewModel(private val gamesRepository: GamesRepository) : ViewModel() {
    val options = listOf("All", "MMORPG", "Shooter", "MOBA", "Anime", "Battle Royale",
        "Strategy", "Fantasy", "Sci-Fi", "Racing")
    var expanded by mutableStateOf(false)
    var selectedOption = mutableStateOf("All")

    var gameUiState: GameUiState by mutableStateOf(GameUiState.Blank)
        private set

    private fun getGames(category: String, platform: String) {
        gameUiState = GameUiState.Loading
        viewModelScope.launch {
            gameUiState = try {
                val result = gamesRepository.getGames(category, platform)
                GameUiState.Success(result)
            } catch (e: IOException) {
                GameUiState.Error
            } catch (e: HttpException) {
                GameUiState.Error
            }
        }
    }
}