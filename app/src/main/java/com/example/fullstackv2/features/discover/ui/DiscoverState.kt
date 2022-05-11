package com.example.fullstackv2.features.discover.ui

import androidx.compose.runtime.Immutable

@Immutable
data class DiscoverState(
    val loading: Boolean,
    val popularMovies: List<MovieUi>,
    val topRatedMovies: List<MovieUi>,
    val upcomingMovies: List<MovieUi>,
)