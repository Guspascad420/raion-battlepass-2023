package com.example.raion_battlepass.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.raion_battlepass.R
import com.example.raion_battlepass.model.GameDetails
import com.example.raion_battlepass.ui.GameDetailsViewModel
import com.example.raion_battlepass.ui.GameUiState
import com.example.raion_battlepass.ui.theme.Roboto
import kotlinx.coroutines.launch

@Composable
fun GameDetailsScreen(
    vm: GameDetailsViewModel = viewModel(factory = GameDetailsViewModel.Factory)
) {
    when (val gameUistate = vm.gameUiState) {
        is GameUiState.Loading -> LoadingScreen()
        is GameUiState.SuccessDetails -> GameDetailsResult(gameUistate.result, viewModel = vm)
        else -> ErrorScreen()
    }
}

@Composable
fun GameDetailsResult(game: GameDetails, viewModel: GameDetailsViewModel) {
    val releaseDate = game.releaseDate.substring(0, 4)
    val coroutineScope = rememberCoroutineScope()
    viewModel.isFavGame(game.title)

    LazyColumn {
        item {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(game.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.games),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                Modifier
                    .padding(top = 10.dp, start = 15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    game.title,
                    Modifier
                        .width(280.dp),
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )

                val iconRes = if (viewModel.isFavorite) {
                    R.drawable.ic_baseline_star_rate
                } else {
                    R.drawable.ic_baseline_star_outline
                }

                val iconTint = if (viewModel.isFavorite) {
                    Color(0xFFFFD600)
                } else {
                    MaterialTheme.colors.onSurface
                }

                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            if (!viewModel.isFavorite) {
                                viewModel.saveFavGame(game)
                            } else {
                                viewModel.deleteFavGame(game)
                            }
                        }
                    }, Modifier.padding(end = 15.dp)
                ) {
                    Icon(
                        painterResource(iconRes),
                        contentDescription = null,
                        Modifier.size(35.dp),
                        tint = iconTint
                    )
                }
            }

            Row(Modifier.padding(top = 5.dp, start = 15.dp)) {
                Text(
                    "$releaseDate - ",
                    fontFamily = Roboto,
                    fontSize = 15.sp
                )
                Text(
                    game.genre,
                    fontFamily = Roboto,
                    fontSize = 15.sp
                )
            }

            Text(
                game.description, fontFamily = Roboto,
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 15.dp)
            )
            Column(Modifier.padding(15.dp)) {
                Row {
                    Text(
                        "Platform  ", fontFamily = Roboto,
                        fontWeight = FontWeight.Bold
                    )
                    Text(game.platform, fontFamily = Roboto)
                }
                Row {
                    Text(
                        "Developer  ", fontFamily = Roboto,
                        fontWeight = FontWeight.Bold
                    )
                    Text(game.developer, fontFamily = Roboto)
                }
            }

        }
    }
}