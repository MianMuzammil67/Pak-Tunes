package com.example.paktunes.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paktunes.data.entities.Song
import com.example.paktunes.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository) : ViewModel() {

//    private val _musicCategory = MutableLiveData<List<Category>>()
//    val artistLiveData: LiveData<List<Category>> = _musicCategory
//
//    private val _podCastCategory = MutableLiveData<List<Category>>()
//    val podCastCategory: LiveData<List<Category>> = _podCastCategory

    private val _filteredSongs = MutableLiveData<List<Song>>()
    val filteredSongsLiveData: LiveData<List<Song>> = _filteredSongs

    private val _songs = MutableLiveData<List<Song>>()
    val songsLiveData: LiveData<List<Song>> = _songs

    init {
        getAllSongs()
    }

    private fun getAllSongs() = viewModelScope.launch {
        val allSongs = repository.getAllSongs() // Fetch from repository
        _songs.value = allSongs // Post the full list of songs to _songs LiveData
    }


//    private fun getAllMusicCategory() = viewModelScope.launch {
//        val artist =  repository.getAllMusicCategories()
//        _musicCategory.postValue(artist)
//
//    }
//    private fun getAllPodCastCategory() = viewModelScope.launch {
//        val artist =  repository.getAllMusicCategories()
//        _podCastCategory.postValue(artist)
//
//    }

    fun getSongsByCategoryName(categoryName : String) {
        val filteredSongs = _songs.value?.filter { song ->
            song.category.equals(categoryName, ignoreCase = true)
        }
        _filteredSongs.postValue(filteredSongs?: emptyList())
    }
}