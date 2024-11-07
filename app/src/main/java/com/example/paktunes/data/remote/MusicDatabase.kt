package com.example.paktunes.data.remote

import android.util.Log
import com.example.paktunes.data.entities.Song
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class MusicDatabase {

    private val firestore = FirebaseFirestore.getInstance()
//    private val songCollection = firestore.collection(SONG_COLLECTION)
    private val songCollection =  firestore.collection("songs")

    suspend fun getAllSongs(): List<Song> {
        return try {
            songCollection.get().await().toObjects(Song::class.java)
        } catch (e: Exception){
            Log.e("MusicDatabase", e.toString())
            emptyList()
        }
    }

}