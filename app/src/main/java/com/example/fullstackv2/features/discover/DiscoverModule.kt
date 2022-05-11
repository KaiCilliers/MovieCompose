package com.example.fullstackv2.features.discover

import com.example.fullstackv2.features.discover.business.MovieRepo
import com.example.fullstackv2.features.discover.business.MovieRepository
import com.example.fullstackv2.features.discover.network.MovieApi
import com.example.fullstackv2.features.discover.network.MovieService
import com.example.fullstackv2.features.discover.ui.DiscoverViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val discoverModule = module {
    single<MovieApi> { MovieService(get()) }
    factory<MovieRepository> { MovieRepo(get()) }
    viewModel { DiscoverViewModel(get()) }
}