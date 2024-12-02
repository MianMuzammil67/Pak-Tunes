package com.example.paktunes.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    val mediaId: String = "",
    val title: String = "",
    val artistName: String = "",
    val songUrl: String = "",
    val imageUrl: String = "",
    val category: String,
): Parcelable

//data class Song(
//    val id: String,  // Using Firestore-generated document ID
//    val title: String,
//    val duration: String,
//    val album: String,
//    val artistId: String,  // Firestore document ID for the artist
//    val categoryName: String,  // Field to store category name (e.g., "Pop", "Rock", etc.)
//    val image: String  // Image URL for the song/album
//)
