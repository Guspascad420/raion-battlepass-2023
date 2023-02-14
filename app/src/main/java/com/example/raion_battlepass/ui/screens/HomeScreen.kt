package com.example.raion_battlepass.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.raion_battlepass.ui.GameViewModel
import com.example.raion_battlepass.ui.theme.Roboto

@Composable
fun HomeScreen() {
    val gameViewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)
    SearchForm(gameViewModel = gameViewModel)
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

            // text field
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
        Button(onClick = { /*TODO*/ }) {
            Text("Search", color = Color.White)
        }
    }
}