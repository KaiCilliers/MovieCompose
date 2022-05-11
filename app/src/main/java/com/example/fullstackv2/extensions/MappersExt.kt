package com.example.fullstackv2.extensions

import com.example.fullstackv2.features.discover.business.Movie
import com.example.fullstackv2.features.discover.network.MovieSingleResponse
import com.example.fullstackv2.features.discover.ui.MovieUi

fun MovieSingleResponse.asDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterUrl = posterPath.orEmpty(),
    )
}

fun List<MovieSingleResponse>.asDomain(): List<Movie> = map { it.asDomain() }

fun Movie.asUiModel(): MovieUi {
    return MovieUi(
        id = id,
        posterUrl = posterUrl,
        title = title
    )
}

fun List<Movie>.asUiModel(): List<MovieUi> {
    return map { it.asUiModel() }
}