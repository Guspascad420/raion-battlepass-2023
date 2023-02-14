package com.example.raion_battlepass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.raion_battlepass.ui.BattlepassApp
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    RaionbattlepassTheme {

    }
}