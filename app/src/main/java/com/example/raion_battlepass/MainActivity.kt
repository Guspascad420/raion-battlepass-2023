package com.example.raion_battlepass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.raion_battlepass.ui.navigation.BattlepassApp
import com.example.raion_battlepass.ui.theme.RaionbattlepassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RaionbattlepassTheme {
                // A surface container using the 'background' color from the theme
                BattlepassApp()
            }
        }
    }
}