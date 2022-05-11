package com.example.fullstackv2.features.discover.network

interface MovieApi {
    suspend fun popularMovies(page: Int = 1): Result<MoviesWrapperResponse>

    suspend fun topRatedMovies(page: Int = 1): Result<MoviesWrapperResponse>

    suspend fun upcomingMovies(page: Int = 1): Result<MoviesWrapperResponse>
}