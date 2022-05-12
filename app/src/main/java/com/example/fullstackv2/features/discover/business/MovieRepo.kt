package com.example.fullstackv2.features.discover.business

import com.example.fullstackv2.extensions.asDomain
import com.example.fullstackv2.features.discover.network.MovieApi
import com.example.fullstackv2.local.MovieLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.Result

class MovieRepo(
    private val service: MovieApi,
    private val db: MovieLocalDataSource
) : MovieRepository {
    override suspend fun popularMovies(page: Int): Flow<List<Movie>> {
        service.popularMovies(page).map {
            it.results.asDomain().forEach {
                db.insert(it)
            }
        }
        return db.all().map { it.map { Movie(
            id = it.id.toInt(),
            title = it.title,
            posterUrl = it.posterUrl
        ) } }
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

    override suspend fun deleteAll() {
        db.deleteAll()
    }
}
