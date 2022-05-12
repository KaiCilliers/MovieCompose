package com.example.fullstackv2.features.discover

import com.example.fullstackv2.CustomNameDatabase
import com.example.fullstackv2.features.discover.business.MovieRepo
import com.example.fullstackv2.features.discover.business.MovieRepository
import com.example.fullstackv2.features.discover.network.MovieApi
import com.example.fullstackv2.features.discover.network.MovieService
import com.example.fullstackv2.features.discover.ui.DiscoverViewModel
import com.example.fullstackv2.local.MovieLocalDataSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val discoverModule = module {
    factory<MovieApi> { MovieService(get()) }
    factory<MovieLocalDataSource> { MovieLocalDataSource.Default(CustomNameDatabase(get())) }
    factory<MovieRepository> { MovieRepo(get(), get()) }
    viewModel { DiscoverViewModel(get()) }
}