package com.example.paktunes.data.remote

import com.example.paktunes.R
import com.example.paktunes.data.entities.Artist
import com.example.paktunes.data.entities.Category
import com.example.paktunes.data.entities.Song
import com.google.firebase.firestore.FirebaseFirestore

class MusicDatabase {

    private val firestore = FirebaseFirestore.getInstance()

    //    private val songCollection = firestore.collection(SONG_COLLECTION)
//    private val songCollection = firestore.collection("songs")

//    suspend fun getAllSongs(): List<Song> {
//        return try {
//            songCollection.get().await().toObjects(Song::class.java)
//        } catch (e: Exception){
//            Log.e("MusicDatabase", e.toString())
//            emptyList()
//        }
//    }

     fun getAllSongs(): List<Song>{
        return getMockSongs()
    }

    private fun getMockSongs(): List<Song> {
        return listOf(
            Song(
                mediaId = "001",
                title = "Meri Kahani",
                subtitle = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/02%20-%20Kinara%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song1-image.jpg"
            ),
            Song(
                mediaId = "002",
                title = "Rabba Sacheya",
                subtitle = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/03%20-%20Rabba%20Sacheya%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "003",
                title = "Mann Hota Hai",
                subtitle = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/04%20-%20Mann%20Hota%20Hai%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "004",
                title = "Joug",
                subtitle = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/05%20-%20Joug%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "005",
                title = "Humrahi",
                subtitle = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/07%20-%20Humrahi%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "006",
                title = "Mai Nee",
                subtitle = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/08%20-%20Mai%20Nee%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "007",
                title = "Kaun Tha (Kapkapi)",
                subtitle = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/09%20-%20Kaun%20Tha%20-%20Kapkapi%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "008",
                title = "Chor Gayai",
                subtitle = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/10%20-%20Chor%20Gayai%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "009",
                title = "Yaaro",
                subtitle = "Atif Aslam",
                songUrl = "http://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/11%20-%20Yaaro%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            ),
            Song(
                mediaId = "010",
                title = "Hangami Halaat",
                subtitle = "Atif Aslam",
                songUrl = "http://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/12%20-%20Hangami%20Halaat%20(Apniisp.Com).mp3",
                imageUrl = "https://example.com/song2-image.jpg"
            )
        )


    }

    fun getAllMusicCategories(): List<Category> {
        return listOf(
            Category(id = 1, name = "Pop", image = "https://static.vecteezy.com/system/resources/previews/007/379/506/non_2x/pop-music-vintage-3d-lettering-retro-bold-font-typeface-pop-art-stylized-text-old-school-style-neon-light-letters-90s-80s-poster-banner-dark-violet-color-background-vector.jpg"),
            Category(id = 2, name = "Rock", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKZaqlA8Xr8ZJWA436exHbjq8JF6QRjQYDvw&s"),
            Category(id = 3, name = "Sufi", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWYhZ5N6kJ4RCPM_GubobYMxb_bA4Y9wiwxQ&s"),
            Category(id = 4, name = "Classical", image = "https://ma-hub.imgix.net/wp-images/2022/04/26202531/Classical-Music-1-scaled.jpg?w=800&h=400&auto=format"),
            Category(id = 5, name = "Hip Hop", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSuELERpDmqSUR6yosb1QS78-DtmpE-cRe4Jg&s"),
            Category(id = 6, name = "Qawwali", image = "https://f4.bcbits.com/img/a4243394611_65"),
            Category(id = 7, name = "Country", image = R.drawable.placeholder.toString()),
            Category(id = 8, name = "Reggae", image = R.drawable.placeholder.toString()),
            Category(id = 9, name = "Blues", image = R.drawable.pop_img.toString()),
            Category(id = 10, name = "R&B", image = R.drawable.placeholder.toString())
        )
    }

    fun getAllPodcastCategories(): List<Category> {
        return listOf(
            Category(id = 1, name = "Technology", image = "https://cdn.pixabay.com/photo/2021/10/11/17/54/technology-6701504_1280.jpg"),
            Category(id = 2, name = "Business", image = "https://cdn.pixabay.com/photo/2018/03/10/12/00/teamwork-3213924_960_720.jpg"),
            Category(id = 3, name = "Comedy", image = R.drawable.placeholder.toString()),
            Category(id = 4, name = "True Crime", image = R.drawable.placeholder.toString()),
            Category(
                id = 5,
                name = "Health & Wellness",
                image = "https://media.licdn.com/dms/image/C4E12AQFFn0BU4JcTMg/article-cover_image-shrink_600_2000/0/1628834009497?e=2147483647&v=beta&t=xZAVkQ-q7U_cgMf15O--a4UL5Ih6OtkaNaPIIp5HvVE"
            ),
            Category(id = 6, name = "Science", image = R.drawable.placeholder.toString()),
            Category(id = 7, name = "Sports", image = "https://cdn.pixabay.com/photo/2013/07/25/11/58/cricket-166931_960_720.jpg"),
            Category(id = 8, name = "Politics", image = "https://cdn.pixabay.com/photo/2017/05/31/23/01/politics-2361943_960_720.jpg"),
            Category(id = 9, name = "History", image = R.drawable.placeholder.toString()),
        )

    }

    fun getAllArtist() : List<Artist>{
        return listOf(
            Artist(id = 1, name = "Atif Aslam", genre = "Pop", image = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2d/Atif_Aslam_at_Badlapur_%28cropped%29.jpg/220px-Atif_Aslam_at_Badlapur_%28cropped%29.jpg"),
            Artist(id = 2, name = "Rahat Fateh Ali Khan", genre = "Qawwali", image = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/Rahat_Fateh_Ali_Khan_%2814021588865%29_%28cropped%29.jpg/220px-Rahat_Fateh_Ali_Khan_%2814021588865%29_%28cropped%29.jpg"),
            Artist(id = 3, name = "Nashit Akhtar", genre = "Pop", image = "https://example.com/images/nashit_akhtar.jpg"),
            Artist(id = 4, name = "Abida Parveen", genre = "Sufi", image = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/Abida_Parveen.jpg/220px-Abida_Parveen.jpg"),
            Artist(id = 5, name = "Ali Zafar", genre = "Pop", image = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Ali_Zafar_at_%27Sa_Re_Ga_Ma_Pa%27_North_America%27_event_%28cropped%29.jpg/220px-Ali_Zafar_at_%27Sa_Re_Ga_Ma_Pa%27_North_America%27_event_%28cropped%29.jpg"),
            Artist(id = 6, name = "Nusrat Fateh Ali Khan", genre = "Qawwali", image = "https://upload.wikimedia.org/wikipedia/en/thumb/5/59/Nusrat_Fateh_Ali_Khan_03_1987_Royal_Albert_Hall.jpg/220px-Nusrat_Fateh_Ali_Khan_03_1987_Royal_Albert_Hall.jpg"),
            Artist(id = 7, name = "Junoon", genre = "Rock", image = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Junoon_Live_at_Channel_V_Awards.jpg/250px-Junoon_Live_at_Channel_V_Awards.jpg"),
            Artist(id = 8, name = "Bilal Saeed", genre = "hip hop", image = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/Bilal_Saeed.jpg/220px-Bilal_Saeed.jpg"),
            Artist(id = 9, name = "Talha Anjum", genre = "hip hop", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzOdbnlzfLbF_kEAXQIe-3QhTDHB3FbCMYyg&s"),
            Artist(id = 10, name = "Farhan Saeed", genre = "Pop", image = "https://upload.wikimedia.org/wikipedia/en/a/ab/Farhan_Saeed.jpeg"),
            Artist(id = 11, name = "Mehdi Hassan", genre = "Classical", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5eTk6BbyYj8U5EPGOrB7diz4XkSv6ILqnp1SAiA50OqePvxGX")
                      )
    }

//    current artis k songs k liy view model main getallsongs main query chlani hy

}