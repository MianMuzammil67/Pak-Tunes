package com.example.paktunes.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paktunes.data.entities.Category
import com.example.paktunes.data.entities.Song
import com.example.paktunes.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MusicViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _playingSongs = MutableLiveData<List<Song>>()
    val playingSongsLiveData: LiveData<List<Song>> = _playingSongs

    private val _filteredSongs = MutableLiveData<List<Song>>()
    val filteredSongsLiveData: LiveData<List<Song>> = _filteredSongs

//    private val _songsByArtistId = MutableLiveData<List<Song>>()
//    val songsByArtistIdLiveData: LiveData<List<Song>> = _filteredSongs

    private val _musicCategories = MutableLiveData<List<Category>>()
    val musicCategories: LiveData<List<Category>> = _musicCategories

    private val _podcastCategories = MutableLiveData<List<Category>>()
    val podcastCategories: LiveData<List<Category>> = _podcastCategories

    val popularSongs: LiveData<List<Song>> = repository.allSongs

    val songsLiveData: LiveData<List<Song>> = repository.allSongs

    private val _currentSongIndex = MutableLiveData(1)
    val currentSongIndex: LiveData<Int> = _currentSongIndex

    private val _currentSong = MediatorLiveData<Song?>()
    val currentSong: LiveData<Song?> = _currentSong


    init {
        getMusicCategories()
        getPodcastCategories()
        _currentSong.addSource(playingSongsLiveData) {
            updateCurrentSong()
        }
        _currentSong.addSource(currentSongIndex) {
            updateCurrentSong()
        }
    }

    private fun updateCurrentSong() {
        Log.d("MusicViewModel", "updateCurrentSong is called")
        val songs = _playingSongs.value ?: emptyList()
        val index = _currentSongIndex.value ?: 0
        _currentSong.value = songs.getOrNull(index)
    }

    fun setPlayingSongs(songsList: List<Song>) {
        Log.d("MusicViewModel", "setPlayingSongs is called with $songsList songs")
        _playingSongs.value = songsList
    }

    private fun getMusicCategories() = viewModelScope.launch {
        val musicCategoryList = repository.getAllMusicCategories()
        _musicCategories.postValue(musicCategoryList)
    }

    private fun getPodcastCategories() = viewModelScope.launch {
        val podcastCategoryList = repository.getAllPodCastCategories()
        _podcastCategories.postValue(podcastCategoryList)
    }


    fun setCurrentSongIndex(index: Int) {
        Log.d("MusicViewModel", " setCurrentSongIndex is called ")

        _currentSongIndex.value = index
        Log.d("MusicViewModel", " index received : $index")
    }

    fun getSongsByCategoryName(categoryName: String) {
        Log.d("MusicViewModel", "getSongsByCategoryName is called")
        val songsList = songsLiveData.value
        if (songsList.isNullOrEmpty()) {
            Log.d("MusicViewModel", "No songs available to filter for $categoryName.")
        } else {
            val filteredSongs = songsList.filter { song ->
                song.category.equals(categoryName, ignoreCase = true)
            }
            Log.d("MusicViewModel", "filteredSongs ===== $filteredSongs")
            _filteredSongs.postValue(filteredSongs)
        }
    }

    fun playNextSong() {
        val currentIndex = _currentSongIndex.value ?: return
        if (currentIndex < (songsLiveData.value?.size ?: 1) - 1) {
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
