package com.example.fullstackv2.features.discover.business

import kotlinx.coroutines.flow.Flow
import kotlin.Result

interface MovieRepository {
    suspend fun popularMovies(page: Int = 1): Flow<List<Movie>>

    suspend fun topRatedMovies(page: Int = 1): Result<List<Movie>>

    suspend fun upcomingMovies(page: Int = 1): Result<List<Movie>>

    suspend fun deleteAll()
}