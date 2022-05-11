package com.example.fullstackv2.features.discover.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.fullstackv2.features.destinations.DetailScreenDestination
import com.example.fullstackv2.ui_common.StableHolder
import com.example.fullstackv2.ui_common.recomposeHighlighter
import com.example.fullstackv2.ui.theme.FullstackV2Theme
import com.example.fullstackv2.ui_common.ImmutableHolder
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun DiscoverScreen(
    vm: DiscoverViewModel = getViewModel(),
    navigator: DestinationsNavigator
) {
    val state = vm.state.collectAsState()

    val scope = rememberCoroutineScope()
    println("the state = $state")
    DiscoverScreenContent(
        state = state.value,
        loadMoreOne = {
            scope.launch {
                vm.fetchOne()
            }
        },
        loadMoreTwo = {
            scope.launch {
                vm.fetchTwo()
            }
        },
        loadMoreThree = {
            scope.launch {
                vm.fetchThree()
            }
        },
        onClick = {
            navigator.navigate(DetailScreenDestination(it))
        }
    )
}

@Composable
fun DiscoverScreenContent(
    state: DiscoverState,
    loadMoreOne: () -> Unit,
    loadMoreTwo: () -> Unit,
    loadMoreThree: () -> Unit,
    onClick: (MovieUi) -> Unit
) {
    SideEffect {
        println("comp")
    }
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .recomposeHighlighter()
    ) {
        Column {
            HorizontalList(type = Type.POPULAR, movies = state.popularMovies, loading = false, loadMore = loadMoreOne, onClick = onClick)
            HorizontalList(type = Type.TOP_RATED, movies = state.topRatedMovies, loading = false, loadMore = loadMoreTwo, onClick = onClick)
            HorizontalList(type = Type.UPCOMING, movies = state.upcomingMovies, loading = false, loadMore = loadMoreThree, onClick = onClick)
            HorizontalList(type = Type.TEST, movies = state.popularMovies, loading = false, loadMore = {}, onClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDiscoverScreen() {
    FullstackV2Theme {

    }
}