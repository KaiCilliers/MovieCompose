package com.example.fullstackv2.features.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.fullstackv2.features.discover.ui.MovieUi
import com.example.fullstackv2.ui.theme.FullstackV2Theme
import com.example.fullstackv2.ui.theme.spacing
import com.example.fullstackv2.ui_common.recomposeHighlighter
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun DetailScreen(
    movie: MovieUi,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .fillMaxSize()
        .recomposeHighlighter()) {
        Row {
            Text(
                text = "${movie.id}",
                modifier = Modifier
                    .recomposeHighlighter()
                    .padding(top = MaterialTheme.spacing.medium, start = MaterialTheme.spacing.small)
            )
            Text(
                text = " - ${movie.title}",
                modifier = Modifier
                    .recomposeHighlighter()
                    .padding(top = MaterialTheme.spacing.medium, start = MaterialTheme.spacing.small)
            )
        }
        AsyncImage(
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.medium, start = MaterialTheme.spacing.small)
                .recomposeHighlighter()
                .height(100.dp)
                .width(80.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/original/${movie.posterUrl}")
                .crossfade(true)
                .build(), contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    FullstackV2Theme {
        DetailScreen(
            movie = MovieUi(
                id = 69,
                title = "Example Movie",
                posterUrl = "/sdEOH0992YZ0QSxgXNIGLq1ToUi.jpg"
            )
        )
    }
}