package com.example.paktunes.repository
//
import com.example.paktunes.data.remote.MusicDatabase
import javax.inject.Inject

class ArtistRepository@Inject constructor(private val musicDatabase: MusicDatabase)  {

    suspend fun getAllSongs() = musicDatabase.getAllSongs()

    fun getAllArtists()= musicDatabase.getAllArtist()



//    private val db = FirebaseFirestore.getInstance()
//
//    // Fetching all artists from Firestore
//    suspend fun getAllArtists(): List<Artist> {
//        return try {
//            val snapshot = db.collection("artists").get().await()  // Firestore collection for artists
//            snapshot.documents.map { doc ->
//                Artist(
//                    id = doc.getLong("id")?.toInt() ?: 0,
//                    name = doc.getString("name") ?: "",
//                    genre = doc.getString("genre") ?: "",
//                    image = doc.getString("image") ?: ""  // Assuming image is a URL stored in Firestore
//                )
//            }
//        } catch (e: Exception) {
//            emptyList() // Return an empty list in case of error
//        }
//    }
//
//    // Fetching songs for a specific artist
//    suspend fun getSongsByArtist(artistId: Int): List<Song> {
//        return try {
//            val snapshot = db.collection("songs")
//                .whereEqualTo("artistId", artistId)
//                .get().await()  // Firestore collection for songs
//            snapshot.documents.map { doc ->
//                Song(
//                    id = doc.getLong("id")?.toInt() ?: 0,
//                    title = doc.getString("title") ?: "",
//                    duration = doc.getString("duration") ?: "",
//                    album = doc.getString("album") ?: "",
//                    artistId = doc.getLong("artistId")?.toInt() ?: 0
//                )
//            }
//        } catch (e: Exception) {
//            emptyList()  // Return empty list in case of error
//        }
//    }
}
