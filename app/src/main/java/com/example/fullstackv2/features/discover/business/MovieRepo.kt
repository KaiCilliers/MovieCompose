package com.example.fullstackv2.features.discover.business

import com.example.fullstackv2.extensions.asDomain
import com.example.fullstackv2.features.discover.network.MovieApi
import kotlin.Result

class MovieRepo(
    private val service: MovieApi
) : MovieRepository {
    override suspend fun popularMovies(page: Int): Result<List<Movie>> {
        return service.popularMovies(page).map {
            it.results.asDomain()
        }.also {
            println("Repo - $it")
        }
    }
    override suspend fun topRatedMovies(page: Int): Result<List<Movie>> {
        return service.topRatedMovies(page).map {
            it.results.asDomain()
        }.also {
            println("Repo - $it")
        }
    }
    override suspend fun upcomingMovies(page: Int): Result<List<Movie>> {
        return service.upcomingMovies(page).map {
            it.results.asDomain()
        }.also {
            println("Repo - $it")
            it.exceptionOrNull()?.printStackTrace()
        }
    }
}
