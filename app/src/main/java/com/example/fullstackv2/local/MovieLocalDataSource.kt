package com.example.fullstackv2.local

import com.example.fullstackv2.CustomNameDatabase
import com.example.fullstackv2.features.discover.business.Movie
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import customname.moviedb.MovieEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface MovieLocalDataSource {

    suspend fun withId(id: Int): MovieEntity?

    fun all(): Flow<List<MovieEntity>>

    suspend fun insert(movie: Movie)

    suspend fun delete(id: Int)

    suspend fun deleteAll()

    class Default(
        db: CustomNameDatabase,
        private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) : MovieLocalDataSource {

        private val queries = db.movieEntityQueries

        override suspend fun withId(id: Int): MovieEntity? {
            return withContext(dispatcher) {
                queries.getMovieById(id.toLong()).executeAsOneOrNull()
            }
        }

        override fun all(): Flow<List<MovieEntity>> {
            return queries.getAllMovies().asFlow().mapToList()
        }

        override suspend fun insert(movie: Movie) {
            withContext(dispatcher) {
                queries.insertMovie(
                    id = movie.id.toLong(),
                    title = movie.title,
                    posterUrl = movie.posterUrl
                )
            }
        }

        override suspend fun delete(id: Int) {
            withContext(dispatcher) {
                queries.deleteMovieById(id.toLong())
            }
        }

        override suspend fun deleteAll() {
            queries.deleteAllMovies()
        }
    }

}