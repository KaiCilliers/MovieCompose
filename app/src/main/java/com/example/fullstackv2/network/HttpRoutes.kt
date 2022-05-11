package com.example.fullstackv2.network

import com.example.fullstackv2.BuildConfig

object HttpRoutes {

    private const val BASE_URL = "https://api.themoviedb.org/3"
    private const val API_KEY = BuildConfig.TMDB_API_KEY

    fun popularMovies(page: Int = 1): String {
        return "$BASE_URL/movie/popular?api_key=$API_KEY&page=$page"
    }

    fun topRatedMovies(page: Int = 1): String {
        return "$BASE_URL/movie/top_rated?api_key=$API_KEY&page=$page"
    }

    fun upcomingMovies(page: Int = 1): String {
        return "$BASE_URL/movie/upcoming?api_key=$API_KEY&page=$page"
    }
}