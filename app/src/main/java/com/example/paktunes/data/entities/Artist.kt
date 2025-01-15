package com.example.paktunes.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artist(
    val id: Int = -1,
    val name: String = "",
    val genre: String = "",
    val description: String = "",
    val image: String = ""// Image URL
): Parcelable