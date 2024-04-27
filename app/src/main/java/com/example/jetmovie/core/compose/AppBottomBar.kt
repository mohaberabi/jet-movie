package com.example.jetmovie.core.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Upcoming
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController


@Composable
fun AppBottomBar(
    selectedIndex: Int = 0,
    onClick: (Int) -> Unit = {}
) {
    val items = listOf(
        BottomItemData("Popular", Icons.Rounded.Movie),
        BottomItemData("Upcoming", Icons.Rounded.Upcoming),
    )

    NavigationBar {

        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {

            items.forEachIndexed { index, item ->

                NavigationBarItem(
                    selected = index == selectedIndex,
                    label = {
                        Text(
                            text = item.title,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    onClick = { onClick(index) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = null
                        )
                    },
                )
            }

        }
    }

}


data class BottomItemData(
    val title: String,
    val icon: ImageVector,
)