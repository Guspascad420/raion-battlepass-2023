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
import com.example.raion_battlepass.ui.screens.FavoriteGameScreen
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
            AppLayout(
                navigateToHome = { navController.navigate(Screen.Games.route) },
                navigateToFavorite = { navController.navigate(Screen.Favorite.route) }
            ) {
                HomeScreen { navController.navigate(Screen.Games.route + "/$it") }
            }
        }
        composable(
            route = Screen.Games.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            AppLayout(
                navigateToHome = { navController.navigate(Screen.Games.route) },
                navigateToFavorite = { navController.navigate(Screen.Favorite.route) }
            ) {
                GameDetailsScreen()
            }
        }
        composable(route = Screen.Favorite.route) {
            AppLayout(
                navigateToHome = { navController.navigate(Screen.Games.route) },
                navigateToFavorite = { navController.navigate(Screen.Favorite.route) }
            ) {
                FavoriteGameScreen(navigateToGameDetails = { navController.navigate(Screen.Games.route + "/$it") })
            }
        }
    }
}

@Composable
fun AppLayout(
    navigateToHome: () -> Unit,
    navigateToFavorite: () -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(bottomBar = {
        GameBottomAppBar(
            navigateToHome = navigateToHome,
            navigateToFavorite = navigateToFavorite
        )
    }) { innerPadding ->
        Surface(
            Modifier
                .fillMaxSize()
                .padding(innerPadding), color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Composable
fun GameBottomAppBar(
    navigateToHome: () -> Unit,
    navigateToFavorite: () -> Unit
) {
    BottomAppBar {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            IconButton(onClick = navigateToHome, Modifier.padding(horizontal = 25.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Home, contentDescription = null)
                    Text(
                        "Home",
                        fontFamily = Roboto
                    )
                }
            }
            IconButton(onClick = navigateToFavorite, Modifier.padding(horizontal = 25.dp)) {
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