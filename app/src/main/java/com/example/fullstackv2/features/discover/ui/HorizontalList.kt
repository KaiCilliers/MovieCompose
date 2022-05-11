package com.example.fullstackv2.features.discover.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.fullstackv2.ui_common.StableHolder
import com.example.fullstackv2.ui_common.recomposeHighlighter
import com.example.fullstackv2.ui.theme.spacing
import com.example.fullstackv2.ui_common.ImmutableHolder

enum class Type {
    POPULAR, TOP_RATED, UPCOMING, TEST
}

@Composable
fun HorizontalList(type:Type, movies: ImmutableHolder<List<MovieUi>>, loading: Boolean, loadMore: () -> Unit, onClick: (MovieUi) -> Unit) {
    SideEffect {
        println("recompile: ${type.name}")
    }
    LazyRow(
        Modifier
            .recomposeHighlighter()
            .padding(bottom = MaterialTheme.spacing.large)
    ) {
        itemsIndexed(movies.item) { i, item ->

            if (i >= movies.item.size - 1 && !loading) {
                println("PETER i = $i and size = ${movies.item.size} and loading: ${loading}")
                loadMore()
            }

            Row {
                AsyncImage(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small)
                        .recomposeHighlighter()
                        .height(100.dp)
                        .width(80.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://image.tmdb.org/t/p/w500/${item.posterUrl}")
                        .crossfade(true)
                        .build(), contentDescription = null
                )
                Text(
                    text = item.title,
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small)
                        .recomposeHighlighter()
                        .width(50.dp)
                        .height(100.dp)
                        .clickable { onClick(item) }
                )
            }
        }
        item {
            if (loading) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .recomposeHighlighter(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .recomposeHighlighter()
                    )
                }
            }
        }
    }
}