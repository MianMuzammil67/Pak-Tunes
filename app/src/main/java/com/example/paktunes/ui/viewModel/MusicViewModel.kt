package com.example.paktunes.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.paktunes.data.entities.Category
import com.example.paktunes.data.entities.Song
import com.example.paktunes.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MusicViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _musicCategories = MutableLiveData<List<Category>>()
    val musicCategories: LiveData<List<Category>> = _musicCategories

    private val _podcastCategories = MutableLiveData<List<Category>>()
    val podcastCategories: LiveData<List<Category>> = _podcastCategories

    private val _popularSongs = MutableLiveData<List<Song>>()
    val popularSongs: LiveData<List<Song>> = _popularSongs



    private val _songs = MutableLiveData<List<Song>>()
    val songsLiveData: LiveData<List<Song>> = _songs

    private val _currentSongIndex = MutableLiveData<Int>()
    val currentSongIndex: LiveData<Int> = _currentSongIndex

    init {
        getMusicCategories()
        getPodcastCategories()
        getPopularSongs()
//        fetchALLSongs()
    }

    //    purana but useful     ///////////////////////////////////////////////////////////////////////
//    val currentSong: LiveData<Song?> = _currentSongIndex.map { index ->
//        _songs.value?.getOrNull(index ?: -1)
//    }

    val currentSong: LiveData<Song?> = _currentSongIndex.map { index ->
        _songs.value?.getOrNull(index)
    }

    //    purana but useful
//    private fun fetchALLSongs() {
//        viewModelScope.launch {
//            val songList = repository.getAllSongs()
//            _songs.value=songList
////            if (songList.isNotEmpty()) {
////                _currentSongIndex.value = 1
////            }
//        }
//    }
    fun getALLSongs() {
        viewModelScope.launch {
            val songList = repository.getAllSongs()
//            _songs.postValue(songList)
        }
    }

    private fun getMusicCategories() = viewModelScope.launch {
        val musicCategoryList = repository.getAllMusicCategories()
        _musicCategories.postValue(musicCategoryList)    }
    private fun getPodcastCategories() = viewModelScope.launch {
        val podcastCategoryList = repository.getAllPodCastCategories()
        _podcastCategories.postValue(podcastCategoryList)    }
    fun getPopularSongs() = viewModelScope.launch {
       val songList = repository.getAllSongs()
        _popularSongs.postValue(songList)
    }



//    fun getSongsByCategory(categoryId: String) {
//        //    current category k songs k liy view model main getallsongs main query chlani hy
//    }

    fun setCurrentSongIndex(index: Int) {
        val songs = _songs.value  // Get the current value of _songs
        if (songs != null && index in songs.indices) {
            _currentSongIndex.value = index
        } else {
            // Optionally handle the case where songs is null or the index is out of bounds
            Log.e("MusicViewModel", "Invalid index or songs list is null")
        }
    }


//    fun setCurrentSongIndex(index: Int) {
//        if (index in _songs.value!!.indices) {
//            _currentSongIndex.value = index
//        }
//    }



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
