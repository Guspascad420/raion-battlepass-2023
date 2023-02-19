package com.example.raion_battlepass.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.raion_battlepass.BattlepassApplication
import com.example.raion_battlepass.data.GamesRepository
import com.example.raion_battlepass.model.FavGame
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoriteGameViewModel(gamesRepository: GamesRepository) : ViewModel() {

    val favGameUiState: StateFlow<FavGameUiState> =
        gamesRepository.getAllFavGamesStream().map { FavGameUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FavGameUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BattlepassApplication
                val gamesRepository = application.container.gamesRepository
                FavoriteGameViewModel(gamesRepository = gamesRepository)
            }
        }
    }

}

data class FavGameUiState(val itemList: List<FavGame> = listOf())