package com.example.raion_battlepass.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.raion_battlepass.BattlepassApplication
import com.example.raion_battlepass.data.GamesRepository
import com.example.raion_battlepass.model.Game
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface GameUiState {
    data class Success(val results: List<Game>) : GameUiState
    object Error : GameUiState
    object Loading : GameUiState
}

class GameViewModel(private val gamesRepository: GamesRepository) : ViewModel() {
    val options = listOf("MMORPG", "Shooter", "MOBA", "Anime",
        "Strategy", "Fantasy", "Sci-Fi", "Racing")
    var expanded by mutableStateOf(false)
    var selectedOption by mutableStateOf(options[0])

    var gameUiState: GameUiState by mutableStateOf(GameUiState.Loading)
        private set

//    init {
//        getAllGames()
//    }

    private fun getAllGames() {
        gameUiState = GameUiState.Loading
        viewModelScope.launch {
            gameUiState = try {
                val result = gamesRepository.getAllGames()
                GameUiState.Success(result.slice(0..9))
            } catch (e: IOException) {
                GameUiState.Error
            } catch (e: HttpException) {
                GameUiState.Error
            }
        }
    }

    private fun getGames(category: String, platform: String) {
        gameUiState = GameUiState.Loading
        viewModelScope.launch {
            gameUiState = try {
                val result = gamesRepository.getGames(category, platform)
                GameUiState.Success(result.slice(0..9))
            } catch (e: IOException) {
                GameUiState.Error
            } catch (e: HttpException) {
                GameUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BattlepassApplication
                val gamesRepository = application.container.gamesRepository
                GameViewModel(gamesRepository = gamesRepository)
            }
        }
    }
}