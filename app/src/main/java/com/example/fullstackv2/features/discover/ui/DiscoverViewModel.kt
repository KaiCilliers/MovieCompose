package com.example.fullstackv2.features.discover.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fullstackv2.extensions.asUiModel
import com.example.fullstackv2.features.discover.business.MovieRepository
import com.example.fullstackv2.features.discover.paging.Paginator
import com.example.fullstackv2.ui_common.StableHolder
import com.zhuinden.flowcombinetuplekt.combineTuple
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DiscoverViewModel(
    private val movieRepo: MovieRepository
) : ViewModel() {

    private val loading = MutableStateFlow(false)

    val popularMovies: MutableStateFlow<List<MovieUi>> = MutableStateFlow(emptyList())
    val topRatedMovies: MutableStateFlow<List<MovieUi>> = MutableStateFlow(emptyList())
    val upcomingMovies: MutableStateFlow<List<MovieUi>> = MutableStateFlow(emptyList())

    private val paginator = Paginator.DefaultPaginator(
        initialKey = 10,
        firstAmountOfPages = 1,
        onLoadUpdated = {
//            loading.value = it
        },
        onRequest = { nextPage ->
            movieRepo.popularMovies(nextPage)
        },
        getNextKey = { currentKey ->
            currentKey + 1
        },
        onError = {

        },
        onSuccuess = { items, newKey ->
            popularMovies.value += items.asUiModel()
        }
    )
    private val paginator2 = Paginator.DefaultPaginator(
        initialKey = 10,
        firstAmountOfPages = 1,
        onLoadUpdated = {
//            loading.value = it
        },
        onRequest = { nextPage ->
            movieRepo.topRatedMovies(nextPage)
        },
        getNextKey = { currentKey ->
            currentKey + 1
        },
        onError = {

        },
        onSuccuess = { items, newKey ->
            topRatedMovies.value += items.asUiModel()
        }
    )
    private val paginator3 = Paginator.DefaultPaginator(
        initialKey = 10,
        firstAmountOfPages = 1,
        onLoadUpdated = {
//            loading.value = it
        },
        onRequest = { nextPage ->
            movieRepo.upcomingMovies(nextPage)
        },
        getNextKey = { currentKey ->
            currentKey + 1
        },
        onError = {
        },
        onSuccuess = { items, newKey ->
            upcomingMovies.value += items.asUiModel()
        }
    )

    val state: StateFlow<DiscoverState> = combineTuple(
        loading,
        popularMovies,
        topRatedMovies,
        upcomingMovies
    ).map { (loading, pop, rated, up) ->
        DiscoverState(loading, StableHolder(pop), StableHolder(rated), StableHolder(up))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), DiscoverState(false, StableHolder(emptyList()), StableHolder(emptyList()), StableHolder(emptyList())))


    init {
        viewModelScope.launch {
            val list = listOf(async { paginator.loadNextItems() },
            async { paginator2.loadNextItems() },
            async { paginator3.loadNextItems() },)
            list.forEach {
                it.await()
            }
        }
    }

    suspend fun fetchOne() {
        paginator.loadNextItems()
    }
    suspend fun fetchTwo() {
        paginator2.loadNextItems()
    }
    suspend fun fetchThree() {
        paginator3.loadNextItems()
    }

}