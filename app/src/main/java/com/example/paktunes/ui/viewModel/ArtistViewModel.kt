package com.example.paktunes.ui.viewModel

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

    private val _artist = MutableLiveData<List<Artist>>()
    val artistLiveData: LiveData<List<Artist>> = _artist

    private val _songs = MutableLiveData<List<Song>>()
    val songsLiveData: LiveData<List<Song>> = _songs

    private val _filteredSongs = MutableLiveData<List<Song>>()
    val filteredSongsLiveData: LiveData<List<Song>> = _filteredSongs

    init {
//        getAllSongs()
        getAllArtist()
    }

    private fun getAllSongs() = viewModelScope.launch {
        val allSongs = repository.getAllSongs() // Fetch from repository
        _songs.postValue(allSongs) // Post the full list of songs to _songs LiveData
    }

    private fun getAllArtist() = viewModelScope.launch {
      val artist =  repository.getAllArtists()
        _artist.postValue(artist)

    }


    fun getSongsByArtistId(artistId : String) {
        // Filter the songs by the artistId (case-insensitive)
        val filteredSongs = _songs.value?.filter { song ->
            song.artistName.equals(artistId, ignoreCase = true)
        }
        _filteredSongs.postValue(filteredSongs!!)

    }

// Filtering songs by the provided artist name (subtitle)
//        return allSongs.filter { song ->
//            song.subtitle.equals(artistName, ignoreCase = true)  // Case insensitive match
//        }
        //    current artis k songs k liy view model main getallsongs main query chlani hy

//    }

}