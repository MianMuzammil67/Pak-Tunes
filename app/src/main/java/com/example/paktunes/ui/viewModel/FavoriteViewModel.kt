package com.example.paktunes.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paktunes.data.entities.Song
import com.example.paktunes.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _favoriteSongs = MutableLiveData<List<Song>>()
    val favoriteSongs: LiveData<List<Song>> = _favoriteSongs

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun getAllFavoriteSongs() = viewModelScope.launch {
        val favorites = mainRepository.getFavorites()
        _favoriteSongs.value = favorites
    }

    fun toggleFavorite(song: Song) = viewModelScope.launch {
        val currentlyFavorite = mainRepository.isFavorite(song)

        if (currentlyFavorite) {
            removeFromFavorite(song)
            _isFavorite.value = false

        } else {
            mainRepository.addToFavorite(song)
            _isFavorite.value = true
        }
    }

    fun removeFromFavorite(song: Song) = viewModelScope.launch {
        mainRepository.removeFromFavorite(song)
    }

    fun isFavorite(song: Song) = viewModelScope.launch {
        _isFavorite.value = mainRepository.isFavorite(song)
    }


}