package com.example.raion_battlepass.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.raion_battlepass.R
import com.example.raion_battlepass.model.Game
import com.example.raion_battlepass.ui.GameUiState
import com.example.raion_battlepass.ui.GameViewModel
import com.example.raion_battlepass.ui.LoadingSpinner
import com.example.raion_battlepass.ui.theme.Roboto

@Composable
fun HomeScreen() {
    val gameViewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)
    val gameUistate = gameViewModel.gameUiState

    Column {
        SearchForm(gameViewModel = gameViewModel)
        when (gameUistate) {
            is GameUiState.Loading -> LoadingScreen()
            is GameUiState.Success -> GameGridScreen(gameUistate.results)
            is GameUiState.Error -> ErrorScreen()
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchForm(gameViewModel: GameViewModel) {
    val options = gameViewModel.options

    Column(Modifier.padding(20.dp)) {
        Text("Categories", fontFamily = Roboto, fontSize = 25.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(10.dp))
        ExposedDropdownMenuBox(
            expanded = gameViewModel.expanded,
            onExpandedChange = {
                gameViewModel.expanded = !gameViewModel.expanded
            }
        ) {
            OutlinedTextField(
                value = gameViewModel.selectedOption,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = gameViewModel.expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    unfocusedBorderColor = Color.Black,
                    trailingIconColor = Color.Black
                ),
                textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                shape = RoundedCornerShape(10.dp)
            )

            DropdownMenu(
                expanded = gameViewModel.expanded,
                onDismissRequest = { gameViewModel.expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            gameViewModel.selectedOption = option
                            gameViewModel.expanded = false
                        },
                    ) {
                        Text(option)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text("Platform", fontFamily = Roboto, fontSize = 25.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Checkbox(
                checked = gameViewModel.isPcChecked,
                onCheckedChange = { gameViewModel.isPcChecked = it },
                colors = CheckboxDefaults.colors(uncheckedColor = Color.Black)
            )
            Text("Pc", modifier = Modifier.padding(top = 12.dp))
            Row(Modifier.padding(start = 10.dp)) {
                Checkbox(
                    checked = gameViewModel.isBrowserChecked,
                    onCheckedChange = { gameViewModel.isBrowserChecked = it },
                    colors = CheckboxDefaults.colors(uncheckedColor = Color.Black)
                )
                Text("Browser", modifier = Modifier.padding(top = 12.dp))
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { gameViewModel.handleClick() }) {
            Text("Search", color = Color.White)
        }
    }
}

@Composable
fun GameGridScreen(result: List<Game>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(top = 8.dp)
    ) {
        items(items = result, key = { game -> game.id }) { game ->
            GameCard(game)
        }
    }
}

@Composable
fun GameCard(game: Game) {
    Card(
        modifier = Modifier
            .padding(horizontal = 14.dp, vertical = 10.dp)
            .fillMaxWidth()
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(game.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.games),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                game.title,
                Modifier.padding(start = 8.dp, top = 6.dp, bottom = 6.dp)
            )
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LoadingSpinner()
    }
}

@Composable
fun ErrorScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.loading_failed))
    }
}