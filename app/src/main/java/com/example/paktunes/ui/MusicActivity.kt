package com.example.paktunes.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.paktunes.R
import com.example.paktunes.data.entities.Song
import com.example.paktunes.databinding.ActivityMusicBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicBinding
//    private val viewModel: MusicViewModell by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val songs: List<Song> = listOf(
            Song(
                mediaId = "001",
                title = "Meri Kahani",
                artistName = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/02%20-%20Kinara%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song1-image.jpg"
            ),
            Song(
                mediaId = "002",
                title = "Rabba Sacheya",
                artistName = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/03%20-%20Rabba%20Sacheya%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "003",
                title = "Mann Hota Hai",
                artistName = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/04%20-%20Mann%20Hota%20Hai%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "004",
                title = "Joug",
                artistName = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/05%20-%20Joug%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "005",
                title = "Humrahi",
                artistName = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/07%20-%20Humrahi%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "006",
                title = "Mai Nee",
                artistName = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/08%20-%20Mai%20Nee%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "007",
                title = "Kaun Tha (Kapkapi)",
                artistName = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/09%20-%20Kaun%20Tha%20-%20Kapkapi%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "008",
                title = "Chor Gayai",
                artistName = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/10%20-%20Chor%20Gayai%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "009",
                title = "Yaaro",
                artistName = "Atif Aslam",
                songUrl = "http://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/11%20-%20Yaaro%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "010",
                title = "Hangami Halaat",
                artistName = "Atif Aslam",
                songUrl = "http://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/12%20-%20Hangami%20Halaat%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),

            // Add more songs here
        )

        val db = FirebaseFirestore.getInstance()

// Loop through the list of songs and push each one to Firestore
        for (song in songs) {
            // Create Firestore references for the artist and category paths
            val artistRef = db.collection("Artist").document("AtifAslam").collection("Songs")
                .document(song.mediaId)
            val categoryRef = db.collection("Categories").document("pop").collection("Songs")
                .document(song.mediaId)

            // Set the song data to both paths
            artistRef.set(song, SetOptions.merge()).addOnSuccessListener {
                println("Song '${song.title}' added to Artist/AtifAslam successfully")
            }.addOnFailureListener { e ->
                println("Error adding song '${song.title}' to Artist/AtifAslam: ${e.message}")
            }

            categoryRef.set(song, SetOptions.merge()).addOnSuccessListener {
                println("Song '${song.title}' added to Categories/pop successfully")
            }.addOnFailureListener { e ->
                println("Error adding song '${song.title}' to Categories/pop: ${e.message}")
            }
        }


//        viewModel.songs.observe(this){ songList ->
//            startMusicService(songList)
//            Log.e("MusicActivity", songList.toString())
//
//
//        }

//        setupUI()

    }

//    private fun setupUI() {
//        binding.playButton.setOnClickListener {
//            sendActionToService(MusicServicee.ACTION_PLAY)
//        }
//
////        binding.pauseButton.setOnClickListener {
////            sendActionToService(MusicServicee.ACTION_PAUSE)
////        }
//
//        binding.nextButton.setOnClickListener {
//            sendActionToService(MusicServicee.ACTION_NEXT)
//        }
//
//        binding.prevButton.setOnClickListener {
//            sendActionToService(MusicServicee.ACTION_PREVIOUS)
//        }
//    }
//
//    private fun sendActionToService(action: String) {
//        val intent = Intent(this, MusicServicee::class.java).apply {
//            this.action = action
//        }
//        startService(intent)
//    }

//    private fun startMusicService(songList: List<Song>) {
//        val intent = Intent(this, MusicServicee::class.java)
//        intent.putParcelableArrayListExtra("song_list", ArrayList(songList))
//        startService(intent)
//    }
}