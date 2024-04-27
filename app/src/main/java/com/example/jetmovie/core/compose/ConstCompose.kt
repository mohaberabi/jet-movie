package com.example.jetmovie.core.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable

fun Center(content: @Composable () -> Unit) {


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}


@Composable

fun ErrorCard(
    error: String,
    onRetry: () -> Unit = {}
) {

    Center {

        Column {
            Text(text = error, style = MaterialTheme.typography.titleLarge.copy(color = Color.Red))
            Button(
                onClick = { onRetry() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {

                Text(
                    text = "Try Again",
                    style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
                )
            }
        }
    }
}

@Composable

fun AppLoader(
) {

    Center {


        CircularProgressIndicator()
    }
}