package com.example.fullstackv2.features.discover.ui

import androidx.compose.runtime.Immutable
import com.example.fullstackv2.ui_common.StableHolder

@Immutable
data class DiscoverState(
    val loading: Boolean,
    val popularMovies: StableHolder<List<MovieUi>>,
    val topRatedMovies: StableHolder<List<MovieUi>>,
    val upcomingMovies: StableHolder<List<MovieUi>>,
)