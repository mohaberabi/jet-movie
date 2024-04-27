package com.example.jetmovie.listing.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.jetmovie.core.compose.RatingBarCompose
import com.example.jetmovie.core.util.getAverageColor
import com.example.jetmovie.listing.data.api.MovieApi
import com.example.jetmovie.listing.data.local.movie.MovieDb
import com.example.jetmovie.listing.domain.model.Movie


@Composable
fun MovieCard(
    movie: Movie,
    onPress: (Movie) -> Unit,
) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest
            .Builder(LocalContext.current)
            .data(MovieApi.IMAGE_BASE_URL + movie.backDropPath).size(Size.ORIGINAL).build()
    ).state
    val defaultColor = MaterialTheme.colorScheme.secondaryContainer
    var dominantColor by remember {
        mutableStateOf(defaultColor)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentHeight()
            .width(200.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.secondaryContainer,
                        dominantColor
                    )
                )
            )
            .clickable {
                onPress(movie)
            }
    ) {


        if (imageState is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(22.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(imageVector = Icons.Rounded.ImageNotSupported, contentDescription = null)

            }
        }
        if (imageState is AsyncImagePainter.State.Success) {


            dominantColor = getAverageColor(
                imageBitmap = imageState.result.drawable.toBitmap().asImageBitmap()
            )


            Image(
                painter = imageState.painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp)
                    .height(250.dp)
                    .clip(RoundedCornerShape(22.dp)),
                contentScale = ContentScale.FillHeight
            )

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = movie.title, maxLines = 1,
                fontSize = 15.sp,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 26.dp, end = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        bottom = 12.dp,
                        top = 4.dp
                    )
            ) {

                RatingBarCompose(
                    starModifier = Modifier.size(18.dp),
                    rating = movie.voteAvg / 2,
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = movie.voteAvg.toString().take(3),
                    maxLines = 1,
                    fontSize = 15.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 4.dp)
                )

            }

        }
    }
}