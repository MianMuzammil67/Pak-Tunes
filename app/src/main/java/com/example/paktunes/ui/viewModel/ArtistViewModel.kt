package com.example.paktunes.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paktunes.data.entities.Artist
import com.example.paktunes.data.entities.Song
import com.example.paktunes.repository.ArtistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(private val repository: ArtistRepository) : ViewModel() {
    val TAG = "ArtistViewModel"


    private val _artist = MutableLiveData<List<Artist>>()
    val artistLiveData: LiveData<List<Artist>> = _artist

    private val _songs = MutableLiveData<List<Song>>()
    val songsLiveData: LiveData<List<Song>> = _songs

    private val _filteredSongs = MutableLiveData<List<Song>>()
    val filteredSongsLiveData: LiveData<List<Song>> = _filteredSongs

    init {
        getAllSongs()
        getAllArtist()
    }

    private fun getAllSongs() = viewModelScope.launch {
        val allSongs = repository.getAllSongs() // Fetch from repository
        Log.d(TAG, "getSongsByArtistId getallSongs: $allSongs")
        _songs.value = allSongs // Post the full list of songs to _songs LiveData
    }

    private fun getAllArtist() = viewModelScope.launch {
      val artist =  repository.getAllArtists()
        _artist.postValue(artist)

    }

    fun getSongsByArtistId(artistName: String) {
        val allSongs = _songs.value
        if (allSongs.isNullOrEmpty()) {
            Log.d(TAG, "No songs loaded yet.")
            return
        }
        Log.d(TAG, "Filtering songs for artist: $artistName")
        // Perform the filter after trimming spaces and ignoring case
        val filteredSongs = allSongs.filter { song ->
            val songArtistName = song.artistName?.trim() ?: ""
            val trimmedArtistName = artistName.trim()
            // Log comparison values
            Log.d(TAG, "Comparing: $songArtistName with $trimmedArtistName")
            songArtistName.equals(trimmedArtistName, ignoreCase = true)
        }
        Log.d(TAG, "getSongsByArtistId filtered songs: $filteredSongs")
        _filteredSongs.postValue(filteredSongs ?: emptyList())
    }






//    fun getSongsByArtistId(artistName : String) {
//        val filteredSongs = _songs.value?.filter { song ->
////            song.artistName.equals(artistName, ignoreCase = true)
//            song.artistName.trim().equals(artistName.trim(), ignoreCase = true)
//
//        }
//        Log.d(TAG, "getSongsByArtistId filtersongs: $filteredSongs")
//        _filteredSongs.postValue(filteredSongs?: emptyList())
//
//    }




// Filtering songs by the provided artist name (subtitle)
//        return allSongs.filter { song ->
//            song.subtitle.equals(artistName, ignoreCase = true)  // Case insensitive match
//        }
        //    current artis k songs k liy view model main getallsongs main query chlani hy

//    }

}