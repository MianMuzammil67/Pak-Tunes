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

    private val _filteredSongs = MutableLiveData<List<Song>>()
    val filteredSongsLiveData: LiveData<List<Song>> = _filteredSongs

    private val _musicCategories = MutableLiveData<List<Category>>()
    val musicCategories: LiveData<List<Category>> = _musicCategories

    private val _podcastCategories = MutableLiveData<List<Category>>()
    val podcastCategories: LiveData<List<Category>> = _podcastCategories

    private val _popularSongs = MutableLiveData<List<Song>>()
    val popularSongs: LiveData<List<Song>> = _popularSongs


    private val _songs = MutableLiveData<List<Song>>()
    val songsLiveData: LiveData<List<Song>> = _songs

    private val _currentSongIndex = MutableLiveData<Int>(1)
    val currentSongIndex: LiveData<Int> = _currentSongIndex


    private val _currentSong = MediatorLiveData<Song?>()
    val currentSong: LiveData<Song?> = _currentSong


    init {
        fetchAllSongs()
        getMusicCategories()
        getPodcastCategories()
        getPopularSongs()

        _currentSong.addSource(filteredSongsLiveData) { songs ->
            updateCurrentSong()
        }

        _currentSong.addSource(currentSongIndex) { index ->
            updateCurrentSong()
        }
    }


    private fun updateCurrentSong() {
        val songs = _filteredSongs.value ?: emptyList()
        val index = _currentSongIndex.value ?: 0
        _currentSong.value = songs.getOrNull(index)
    }


    private fun fetchAllSongs() = viewModelScope.launch {
        Log.d("MusicViewModel", "fetchAllSongs is called")
        // Fetch the songs from the repository
        val songList = repository.getAllSongs()
        Log.d("MusicViewModel", "fetchAllSongs : $songList")
        _songs.value = songList

    }

    private fun getMusicCategories() = viewModelScope.launch {
        val musicCategoryList = repository.getAllMusicCategories()
        _musicCategories.postValue(musicCategoryList)
    }

    private fun getPodcastCategories() = viewModelScope.launch {
        val podcastCategoryList = repository.getAllPodCastCategories()
        _podcastCategories.postValue(podcastCategoryList)
    }

    private fun getPopularSongs() = viewModelScope.launch {
        val songList = repository.getAllSongs()
        _popularSongs.postValue(songList)
    }


    fun setCurrentSongIndex(index: Int) {
        Log.d("MusicViewModel", " setCurrentSongIndex is called ")

        _currentSongIndex.value = index
        Log.d("MusicViewModel", " index received : $index")
    }

    fun getSongsByCategoryName(categoryName: String) {
//        fetchAllSongs()
        Log.d("MusicViewModel", "getSongsByCategoryName is called")

        val songsList = _songs.value
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
