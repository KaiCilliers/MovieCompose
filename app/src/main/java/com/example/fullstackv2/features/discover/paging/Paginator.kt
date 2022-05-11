package com.example.fullstackv2.features.discover.paging

import kotlinx.coroutines.delay

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()

    class DefaultPaginator<Key, Item>(
        private val initialKey: Key,
        private var firstAmountOfPages: Int = 1,
        // Called when loading state changes
        private inline val onLoadUpdated: (Boolean) -> Unit,
        // Define how to get next page of items using key
        private inline val onRequest: suspend (nextKey: Key) -> Result<List<Item>>,
        // logic to get the next key
        private inline val getNextKey: suspend (currentKey: Key) -> Key,
        private inline val onError: suspend (Throwable?) -> Unit,
        private inline val onSuccuess: suspend (items: List<Item>, newKey: Key) -> Unit
    ) : Paginator<Key, Item> {

        private var currentKey: Key = initialKey
        private var isMakingRequest = false
        private var firstLoad = true

        override suspend fun loadNextItems() {
            if (isMakingRequest) {
                return
            }
            isMakingRequest = true
            onLoadUpdated(true)
            var items = mutableListOf<Item>()
            if (!firstLoad) {
                firstAmountOfPages = 1
            }
            (0..firstAmountOfPages).forEach { _ ->
                val result = onRequest(currentKey)
                result.getOrElse {
                    onError(it)
                    onLoadUpdated(false)
                    return
                }.also {
                    items += it
                }
                firstLoad = false
            }
            isMakingRequest = false
            currentKey = getNextKey(currentKey)
            onSuccuess(items, currentKey)
            onLoadUpdated(false)
        }

        override fun reset() {
            currentKey = initialKey
        }
    }
}