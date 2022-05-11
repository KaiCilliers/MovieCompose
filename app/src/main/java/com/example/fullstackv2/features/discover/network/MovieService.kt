package com.example.fullstackv2.features.discover.network

import com.example.fullstackv2.network.HttpRoutes
import com.example.fullstackv2.extensions.safeApi
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlin.Result

class MovieService(
    private val client: HttpClient
) : MovieApi {
    override suspend fun popularMovies(page: Int): Result<MoviesWrapperResponse> = safeApi {
        client.get {
            url(HttpRoutes.popularMovies(page))
        }.body()
    }

    override suspend fun topRatedMovies(page: Int): Result<MoviesWrapperResponse> = safeApi {
        client.get {
            url(HttpRoutes.topRatedMovies(page))
        }.body()
    }

    override suspend fun upcomingMovies(page: Int): Result<MoviesWrapperResponse> = safeApi {
        client.get {
            url(HttpRoutes.upcomingMovies(page))
        }.body()
    }
}