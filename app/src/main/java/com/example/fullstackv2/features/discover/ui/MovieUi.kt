package com.example.fullstackv2.features.discover.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUi(
    val id: Int,
    val posterUrl: String,
    val title: String,
): Parcelable
