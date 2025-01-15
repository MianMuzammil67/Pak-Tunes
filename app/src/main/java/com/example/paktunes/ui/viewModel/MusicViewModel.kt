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

    private var curIndex = 0
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

    private val _currentSongIndex = MutableLiveData<Int>(-1)
    val currentSongIndex: LiveData<Int> = _currentSongIndex

    private val _currentSong = MutableLiveData<Song>()
    val currentSongLiveData: LiveData<Song> = _currentSong


    init {
        fetchAllSongs()
        getMusicCategories()
        getPodcastCategories()
        getPopularSongs()
//        _currentSongIndex.value = -1
    }

    //    purana but useful     ///////////////////////////////////////////////////////////////////////
//    val currentSong: LiveData<Song?> = _currentSongIndex.map { index ->
//        _songs.value?.getOrNull(index ?: -1)
//    }

//    ----------------------------------
//    val currentSong: LiveData<Song?> = _currentSongIndex.map { index ->
//        Log.e("MusicViewModel", "index in currentSong is ======== $index")
//        _filteredSongs.value?.getOrNull(index)
//    }

//    val currentSong: LiveData<Song?> = _currentSongIndex.switchMap { index ->
//        Log.e("MusicViewModel", "index in currentSong is ======== $index")
//        // Using a switchMap here because it listens for changes to _currentSongIndex
//        val song = _filteredSongs.value?.getOrNull(index)
//        MutableLiveData(song)
//    }


//    fun getCurrentSong(index: Int): LiveData<Song?> {
//        _currentSongIndex.value = index
//        val value = filteredSongsLiveData.map { songs ->
//            songs.getOrNull(index)
//        }
//        Log.e("MusicViewModel", "index in currentSong is ======== ${value.value}")
//        return value
//    }


     fun getCurrentSongg() {
        _filteredSongs.observeForever { songs ->
            _currentSongIndex.value?.let { index ->
                Log.d("MusicViewModel", "index before : $index")
                if (index >= 0 && index < songs.size) {
                    // Fetch the song using the index from filtered songs list
                    _currentSong.value = songs[index]
                    Log.d("MusicViewModel", "getCurrentSong: $index,---$songs")
                }
            }
        }
    }

    fun getCurrentSong(): LiveData<Song?> {
        return _filteredSongs.map { songs ->
            if (songs.isNullOrEmpty()) {
                Log.d("MusicViewModel", "getCurrentSong: songs list is empty")
                return@map null
            }

            val safeIndex = _currentSongIndex.value?.coerceIn(0, songs.lastIndex) ?: 0
            Log.d("MusicViewModel", "getCurrentSong: safeIndex = $safeIndex")

            songs.getOrNull(safeIndex)
        }
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
    private fun fetchAllSongs() = viewModelScope.launch {
        Log.d("MusicViewModel", "fetchAllSongs is called")
        // Fetch the songs from the repository
        val songList = repository.getAllSongs()
        Log.d("MusicViewModel", "fetchAllSongs : $songList")
//        _songs.postValue(songList)
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


//    fun getSongsByCategory(categoryId: String) {
//        //    current category k songs k liy view model main getallsongs main query chlani hy
//    }

    fun setCurrentSongIndex(index: Int) {

        _currentSongIndex.postValue(index)
        Log.d("MusicViewModel", " index received : $index")

//        curIndex = index
//        val songs = _filteredSongs.value  // Get the current value of _songs
//        if (songs != null && index in songs.indices) {
//            _currentSongIndex.value
//        _currentSongIndex.value = index
//            Log.e("MusicViewModel", "index form RV ---=== $index")
//        } else {
//            // Optionally handle the case where songs is null or the index is out of bounds
//            Log.e("MusicViewModel", "Invalid index or songs list is null")
//        }
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

//    fun getSongsByCategoryName(categoryName: String) {
//        val filteredSongs = _songs.value?.filter { song ->
//            song.category.equals(categoryName, ignoreCase = true)
//        }
//        Log.d("MusicViewModel", "filteredSongs ===== $filteredSongs")
//        _filteredSongs.postValue(filteredSongs ?: emptyList())
//    }

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
