package com.example.paktunes.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.paktunes.data.entities.Song
import com.example.paktunes.data.remote.MusicDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainRepository @Inject constructor(private val musicDatabase: MusicDatabase) {
    private val _allSongs = MutableLiveData<List<Song>>()
    val allSongs: LiveData<List<Song>> = _allSongs

    private val applicationScope = CoroutineScope(Dispatchers.Default) // Application scope

    init {
        loadSongs()
    }
//    suspend fun getAllSongs() = musicDatabase.getAllSongs()

    private fun loadSongs() {
        Log.d("SongRepository", "loadSongs is called")

        // Check if data is already loaded (optional optimization)
        if (_allSongs.value != null) return
        applicationScope.launch { // Use applicationScope
            try {
                val songs = musicDatabase.getAllSongs()
                _allSongs.postValue(songs)
            } catch (e: Exception) {
                _allSongs.postValue(emptyList())
                Log.e("SongRepository", "Error loading songs: ${e.message}")
            }
        }
    }



    suspend fun getAllMusicCategories() = musicDatabase.getAllMusicCategories()
    suspend fun getAllPodCastCategories() = musicDatabase.getAllPodcastCategories()
    suspend fun getAllArtists() = musicDatabase.getAllArtist()


}