package com.example.fullstackv2.ui_common

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

// Intended to help with making composable functions skippable
@Stable
class StableHolder<T>(val item: T) {
    operator fun component1(): T = item
}

@Immutable
class ImmutableHolder<T>(val item: T) {
    operator fun component1(): T = item
}