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
    private var count = 0

    private val popularMovies: StateFlow<List<MovieUi>> = flow {
        val s = movieRepo.popularMovies(++count).mapLatest { it.map { MovieUi(
            id = it.id,
            title = it.title,
            posterUrl = it.posterUrl
        ) } }
        emitAll(s)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())
    private val topRatedMovies: MutableStateFlow<List<MovieUi>> = MutableStateFlow(emptyList())
    private val upcomingMovies: MutableStateFlow<List<MovieUi>> = MutableStateFlow(emptyList())

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
        DiscoverState(loading, pop, rated, up)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), DiscoverState(false, emptyList(), emptyList(), emptyList()))


    init {
        viewModelScope.launch {
            movieRepo.deleteAll()
            val list = listOf(
            async { paginator2.loadNextItems() },
            async { paginator3.loadNextItems() },)
            list.forEach {
                it.await()
            }
        }
    }

    suspend fun fetchOne() {
        println("KAI COUNT - $count")
        movieRepo.popularMovies(++count)
    }
    suspend fun fetchTwo() {
        paginator2.loadNextItems()
    }
    suspend fun fetchThree() {
        paginator3.loadNextItems()
    }

}