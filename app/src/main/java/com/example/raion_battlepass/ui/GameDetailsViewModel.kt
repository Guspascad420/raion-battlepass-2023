package com.example.raion_battlepass.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.raion_battlepass.BattlepassApplication
import com.example.raion_battlepass.data.GamesRepository
import com.example.raion_battlepass.model.FavGame
import com.example.raion_battlepass.model.GameDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class GameDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val gamesRepository: GamesRepository
) : ViewModel() {
    private val gameId: Int = checkNotNull(savedStateHandle["id"])

    var gameUiState: GameUiState by mutableStateOf(GameUiState.Loading)
        private set
    var isFavorite by mutableStateOf(false)
    var favGameResponse by mutableStateOf(FavGame(0 , "", "", "", ""))

    init {
        getGameDetails(gameId)
    }

    private fun getGameDetails(gameId: Int) {
        viewModelScope.launch {
            gameUiState = try {
                val result = gamesRepository.getGameDetails(gameId)
                GameUiState.SuccessDetails(result)
            } catch (e: IOException) {
                GameUiState.Error
            } catch (e: HttpException) {
                GameUiState.Error
            }
        }
    }

    fun isFavGame(title: String) {
        viewModelScope.launch {
            gamesRepository.getFavGame(title).collect {
                if (it != null) {
                    favGameResponse = it
                    isFavorite = true
                } else {
                    isFavorite = false
                }
            }
        }
    }

    suspend fun saveFavGame(game: GameDetails) {
        gamesRepository.insertFavGame(game.toFavGame())
    }
    suspend fun deleteFavGame(game: GameDetails) {
        gamesRepository.deleteFavGame(game.toFavGame())
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BattlepassApplication
                val gamesRepository = application.container.gamesRepository
                GameDetailsViewModel(this.createSavedStateHandle(), gamesRepository)
            }
        }
    }

}

fun GameDetails.toFavGame(): FavGame = FavGame(
    id = id,
    title = title,
    release_date = releaseDate,
    platform = platform,
    publisher = publisher
)