package com.example.paktunes.repository

import com.example.paktunes.data.remote.MusicDatabase
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val musicDatabase: MusicDatabase) {

    suspend fun getAllSongs() = musicDatabase.getAllSongs()

    suspend fun getAllMusicCategories() = musicDatabase.getAllMusicCategories()
    suspend fun getAllPodCastCategories() = musicDatabase.getAllPodcastCategories()

}