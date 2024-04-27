package com.example.jetmovie.core.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.math.ceil
import kotlin.math.floor


@Composable
fun RatingBarCompose(
    modifier: Modifier = Modifier,

    starModifier: Modifier = Modifier,

    rating: Double = 0.0,
    stars: Int = 5,
    starColor: Color = Color.Yellow,

    ) {
    val filledStars = floor(rating).toInt()
    val unfilledStart = floor(stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                modifier = starModifier,
                imageVector = Icons.Rounded.Star,
                tint = starColor,
                contentDescription = null
            )
        }
        if (halfStar) {
            Icon(
                modifier = starModifier,
                imageVector = Icons.Rounded.StarHalf,
                tint = starColor,
                contentDescription = null
            )
        }
        repeat(unfilledStart) {
            Icon(
                modifier = starModifier,
                imageVector = Icons.Rounded.StarOutline,
                tint = starColor,
                contentDescription = null
            )
        }

    }
}