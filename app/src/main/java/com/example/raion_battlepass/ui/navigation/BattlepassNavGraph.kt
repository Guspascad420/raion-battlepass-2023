package com.example.raion_battlepass.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.raion_battlepass.ui.screens.GameDetailsScreen
import com.example.raion_battlepass.ui.screens.HomeScreen
import com.example.raion_battlepass.ui.theme.Roboto

@Composable
fun BattlepassApp(navController: NavHostController = rememberNavController()) {
    BattlepassNavHost(navController = navController)
}

@Composable
fun BattlepassNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Games.route) {
        composable(route = Screen.Games.route) {
            Scaffold(bottomBar = { GameBottomAppBar() }) { innerPadding ->
                Surface(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding), color = MaterialTheme.colors.background
                ) {
                    HomeScreen { navController.navigate(Screen.Games.route + "/$it") }
                }
            }
        }
        composable(
            route = Screen.Games.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            Scaffold(bottomBar = { GameBottomAppBar() }) {
                Surface(
                    Modifier
                        .fillMaxSize()
                        .padding(it), color = MaterialTheme.colors.background
                ) {
                    GameDetailsScreen()
                }
            }
        }
    }
}

@Composable
fun GameBottomAppBar() {
    BottomAppBar {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            IconButton(onClick = { /*TODO*/ }, Modifier.padding(horizontal = 25.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Home, contentDescription = null)
                    Text(
                        "Home",
                        fontFamily = Roboto
                    )
                }

            }
            IconButton(onClick = { /*TODO*/ }, Modifier.padding(horizontal = 25.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Star, contentDescription = null)
                    Text(
                        "Favorite",
                        fontFamily = Roboto
                    )
                }
            }
        }
    }
}