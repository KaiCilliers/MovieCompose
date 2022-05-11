package com.example.fullstackv2.features.discover.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesWrapperResponse(
    val page: Int,
    val results: List<MovieSingleResponse>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)