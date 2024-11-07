package com.example.paktunes.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.paktunes.data.entities.Song
import com.example.paktunes.data.remote.MusicDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class MusicViewModel @Inject constructor(private val musicDatabase: MusicDatabase) : ViewModel() {

    private val _songs = MutableLiveData<List<Song>>()
    val songsLiveData: LiveData<List<Song>> = _songs

    private val _currentSongIndex = MutableLiveData<Int>()
    val currentSongIndex: LiveData<Int> = _currentSongIndex

//    val currentSong: LiveData<Song?> = Transformations.map(_currentSongIndex) { index ->
//        _songs.value?.getOrNull(index ?: -1)
//    }
    val currentSong: LiveData<Song?> = _currentSongIndex.map { index ->
        _songs.value?.getOrNull(index ?: -1)
    }

    init {
        fetchSongs()
    }

    private fun fetchSongs() {
        viewModelScope.launch {
            val songList = musicDatabase.getAllSongs()
            _songs.postValue(songList)
            if (songList.isNotEmpty()) {
                _currentSongIndex.postValue(1)
            }
        }
    }

    fun setCurrentSongIndex(index: Int) {
        if (index in _songs.value!!.indices) {
            _currentSongIndex.value = index
        }
    }

    fun playNextSong() {
        val currentIndex = _currentSongIndex.value ?: return
        if (currentIndex < (_songs.value?.size ?: 1) - 1) {
            _currentSongIndex.value = currentIndex + 1
        }
    }

    fun playPreviousSong() {
        val currentIndex = _currentSongIndex.value ?: return
        if (currentIndex > 0) {
            _currentSongIndex.value = currentIndex - 1
        }
    }

}
