package com.example.paktunes.data.remote

import com.example.paktunes.R
import com.example.paktunes.data.entities.Artist
import com.example.paktunes.data.entities.Category
import com.example.paktunes.data.entities.Song

class MusicDatabase {

//    private val firestore = FirebaseFirestore.getInstance()

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
                artistName = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/02%20-%20Kinara%20(Apniisp.Com).mp3",
                imageUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/f/f6/Meri_Kahani_cover.jpg/220px-Meri_Kahani_cover.jpg",
                category = "Pop"
            ),
            Song(
                mediaId = "002",
                title = "Rabba Sacheya",
                artistName = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/03%20-%20Rabba%20Sacheya%20(Apniisp.Com).mp3",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwg7_r-7IWfdgsYn1hJmdsPOfZzu0YpB6wqQ&s",
                category = "Pop"
            ),
            Song(
                mediaId = "003",
                title = "Mann Hota Hai",
                artistName = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/04%20-%20Mann%20Hota%20Hai%20(Apniisp.Com).mp3",
                imageUrl = "https://www.bbc.co.uk/staticarchive/50cf0c0b92f670f72bcb5a7d99ba93e94356786e.jpg",
                category = "Romantic"
            ),
            Song(
                mediaId = "005",
                title = "Humrahi",
                artistName = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/07%20-%20Humrahi%20(Apniisp.Com).mp3",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTG2GgO8kPoOILvDXiUJAvSXy-ncOP8xIhFew&s",
                category = "Pop"
            ),
            Song(
                mediaId = "007",
                title = "Kaun Tha (Kapkapi)",
                artistName = "Atif Aslam",
                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/09%20-%20Kaun%20Tha%20-%20Kapkapi%20(Apniisp.Com).mp3",
                imageUrl = "https://www.bbc.co.uk/staticarchive/50cf0c0b92f670f72bcb5a7d99ba93e94356786e.jpg",
                category = "Pop"
            ),
            Song(
                mediaId = "009",
                title = "Yaaro",
                artistName = "Atif Aslam",
                songUrl = "http://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/11%20-%20Yaaro%20(Apniisp.Com).mp3",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRf2kzebrh6GLMr3VM1eukp3dpSaGg_LMjlig&s",
                category = "Romantic"
            ),
            Song(
                mediaId = "11",
                title = "Kahani Suno (Mujhe Pyar Hua Tha)",
                artistName = "Kaifi Khalil",
                songUrl = "http://songs.apniisp.com/Mix%20-%20Songs/323%20-%20Kahani%20Suno%20-%20Kaifi%20Khalil%20(ApniISP.Com).mp3",
                imageUrl = "https://i.ytimg.com/vi/_XBVWlI8TsQ/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLAAX0i887kNXH0IAlODeNs3fV_CvQ",
                category = "Pop"
            ),
            Song(
                mediaId = "12",
                title = "Iraaday (Kaisa Sama Hai)",
                artistName = "Abdul Hannan",
                songUrl = "http://songs.apniisp.com/Mix%20-%20Songs/322%20-%20Iraaday%20-%20Abdul%20Hannan%20(ApniISP.Com).mp3",
                imageUrl = "https://i.ytimg.com/vi/_rOZUz4AIw8/sddefault.jpg",
                category = "Romantic"
            ),
            Song(
                mediaId = "13",
                title = "Habibi",
                artistName = "Asim Azhar",
                songUrl = "http://songs.apniisp.com/Mix%20-%20Songs/321%20-%20Habibi%20-%20Asim%20Azhar%20(ApniISP.Com).mp3",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpPu9bzcCfb4lKuTDPh6lv7QWaqAF5Dffu_A&s",
                category = "Pop"
            ),
            Song(
                mediaId = "13",
                title = "Na Cher Malangaan Nu",
                artistName = "Farhan Saeed",
                songUrl = "http://songs.apniisp.com/Mix%20-%20Songs/319%20-%20Na%20Cher%20Malangaan%20Nu%20-%20Farhan%20Saeed%20&%20Aima%20Baig%20(ApniISP.Com).mp3",
                imageUrl = "https://i1.sndcdn.com/artworks-2pRHe8jhmlzkqOu4-UAc1Cw-t500x500.jpg",
                category = "Folk"
            )
        )


        /*
      //        return listOf(
      //            Song(
      //                mediaId = "001",
      //                title = "Meri Kahani",
      //                artistName = "Atif Aslam",
      //                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/02%20-%20Kinara%20(Apniisp.Com).mp3",
      //                imageUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/f/f6/Meri_Kahani_cover.jpg/220px-Meri_Kahani_cover.jpg"
      //            ),
      //            Song(
      //                mediaId = "002",
      //                title = "Rabba Sacheya",
      //                artistName = "Atif Aslam",
      //                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/03%20-%20Rabba%20Sacheya%20(Apniisp.Com).mp3",
      //                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwg7_r-7IWfdgsYn1hJmdsPOfZzu0YpB6wqQ&s"
      //            ),
      //            Song(
      //                mediaId = "003",
      //                title = "Mann Hota Hai",
      //                artistName = "Atif Aslam",
      //                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/04%20-%20Mann%20Hota%20Hai%20(Apniisp.Com).mp3",
      //                imageUrl = "https://www.bbc.co.uk/staticarchive/50cf0c0b92f670f72bcb5a7d99ba93e94356786e.jpg"
      //            ),
      ////            Song(
      ////                mediaId = "004",
      ////                title = "Joug",
      ////                subtitle = "Atif Aslam",
      ////                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/05%20-%20Joug%20(Apniisp.Com).mp3",
      ////                imageUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/f/f6/Meri_Kahani_cover.jpg/220px-Meri_Kahani_cover.jpg"
      ////            ),
      //            Song(
      //                mediaId = "005",
      //                title = "Humrahi",
      //                artistName = "Atif Aslam",
      //                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/07%20-%20Humrahi%20(Apniisp.Com).mp3",
      //                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTG2GgO8kPoOILvDXiUJAvSXy-ncOP8xIhFew&s"
      //            ),
      ////            Song(
      ////                mediaId = "006",
      ////                title = "Mai Nee",
      ////                subtitle = "Atif Aslam",
      ////                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/08%20-%20Mai%20Nee%20(Apniisp.Com).mp3",
      ////                imageUrl = "https://example.com/song2-image.jpg"
      ////            ),
      //            Song(
      //                mediaId = "007",
      //                title = "Kaun Tha (Kapkapi)",
      //                artistName = "Atif Aslam",
      //                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/09%20-%20Kaun%20Tha%20-%20Kapkapi%20(Apniisp.Com).mp3",
      //                imageUrl = "https://www.bbc.co.uk/staticarchive/50cf0c0b92f670f72bcb5a7d99ba93e94356786e.jpg"
      //            ),
      ////            Song(
      ////                mediaId = "008",
      ////                title = "Chor Gayai",
      ////                subtitle = "Atif Aslam",
      ////                songUrl = "https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/10%20-%20Chor%20Gayai%20(Apniisp.Com).mp3",
      ////                imageUrl = "https://example.com/song2-image.jpg"
      ////            ),
      //            Song(
      //                mediaId = "009",
      //                title = "Yaaro",
      //                artistName = "Atif Aslam",
      //                songUrl = "http://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/11%20-%20Yaaro%20(Apniisp.Com).mp3",
      //                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRf2kzebrh6GLMr3VM1eukp3dpSaGg_LMjlig&s"
      //            ),
      ////            Song(
      ////                mediaId = "010",
      ////                title = "Hangami Halaat",
      ////                subtitle = "Atif Aslam",
      ////                songUrl = "http://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/12%20-%20Hangami%20Halaat%20(Apniisp.Com).mp3",
      ////                imageUrl = "https://example.com/song2-image.jpg"
      ////            ),
      //            Song(
      //                mediaId = "11",
      //                title = "Kahani Suno (Mujhe Pyar Hua Tha)",
      //                artistName = "Kaifi Khalil",
      //                songUrl = "http://songs.apniisp.com/Mix%20-%20Songs/323%20-%20Kahani%20Suno%20-%20Kaifi%20Khalil%20(ApniISP.Com).mp3",
      //                imageUrl = "https://i.ytimg.com/vi/_XBVWlI8TsQ/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLAAX0i887kNXH0IAlODeNs3fV_CvQ"
      //            ),
      //            Song(
      //                mediaId = "12",
      //                title = "Iraaday (Kaisa Sama Hai)",
      //                artistName = "Abdul Hannan",
      //                songUrl = "http://songs.apniisp.com/Mix%20-%20Songs/322%20-%20Iraaday%20-%20Abdul%20Hannan%20(ApniISP.Com).mp3",
      //                imageUrl = "https://i.ytimg.com/vi/_rOZUz4AIw8/sddefault.jpg"
      //            ),
      //            Song(
      //                mediaId = "13",
      //                title = "Habibi",
      //                artistName = "Asim Azhar",
      //                songUrl = "http://songs.apniisp.com/Mix%20-%20Songs/321%20-%20Habibi%20-%20Asim%20Azhar%20(ApniISP.Com).mp3",
      //                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpPu9bzcCfb4lKuTDPh6lv7QWaqAF5Dffu_A&s"
      //            ),
      //            Song(
      //                mediaId = "13",
      //                title = "Na Cher Malangaan Nu",
      //                artistName = "Farhan Saeed",
      //                songUrl = "http://songs.apniisp.com/Mix%20-%20Songs/319%20-%20Na%20Cher%20Malangaan%20Nu%20-%20Farhan%20Saeed%20&%20Aima%20Baig%20(ApniISP.Com).mp3",
      //                imageUrl = "https://i1.sndcdn.com/artworks-2pRHe8jhmlzkqOu4-UAc1Cw-t500x500.jpg"
      //            )
      //        )
      */

    }

    fun getAllMusicCategories(): List<Category> {
        return listOf(
            Category(id = 1, name = "Pop", image = "https://static.vecteezy.com/system/resources/previews/007/379/506/non_2x/pop-music-vintage-3d-lettering-retro-bold-font-typeface-pop-art-stylized-text-old-school-style-neon-light-letters-90s-80s-poster-banner-dark-violet-color-background-vector.jpg"),
            Category(id = 2, name = "Rock", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKZaqlA8Xr8ZJWA436exHbjq8JF6QRjQYDvw&s"),
            Category(id = 3, name = "Sufi", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWYhZ5N6kJ4RCPM_GubobYMxb_bA4Y9wiwxQ&s"),
            Category(id = 4, name = "Classical", image = "https://ma-hub.imgix.net/wp-images/2022/04/26202531/Classical-Music-1-scaled.jpg?w=800&h=400&auto=format"),
            Category(id = 5, name = "Hip Hop", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSuELERpDmqSUR6yosb1QS78-DtmpE-cRe4Jg&s"),
            Category(id = 6, name = "Qawwali", image = "https://f4.bcbits.com/img/a4243394611_65"),
            Category(id = 7, name = "Folk", image = "https://ih1.redbubble.net/image.5162404156.9480/flat,750x,075,f-pad,750x1000,f8f8f8.jpg"),
            Category(id = 8, name = "Romantic", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSKjHPmj6qfsuaQTpkkkrL0a_8SWcD4lE9caw&s"),
            Category(id = 9, name = "Blues", image = "https://ih1.redbubble.net/image.554049168.5499/flat,750x,075,f-pad,750x1000,f8f8f8.u5.jpg"),
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
                Artist(id = 1, name = "Atif Aslam", genre = "Pop", description = "Atif Aslam – A renowned Pakistani singer known for his soulful voice and hits in Bollywood and Pakistani music, primarily in the pop genre.", image = "https://i1.sndcdn.com/artworks-000076447394-g1y3e7-t500x500.jpg"),
        Artist(id = 2, name = "Rahat Fateh Ali Khan", genre = "Qawwali", description = "Rahat Fateh Ali Khan – A legendary qawwali singer from Pakistan, famous for his powerful and soulful performances in Sufi and devotional music.", image = "https://m.media-amazon.com/images/M/MV5BM2RjOGFjMTktMDhmOS00ODA5LWEwMjctMzZhNWE0ODJlZjliXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg"),
        Artist(id = 3, name = "Asim Azhar", genre = "Pop", description = "Asim Azhar – A popular Pakistani pop singer and songwriter, known for his modern sound and contributions to the youth music scene.", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTOXLkXwHFQU2A6TWCybF0oc4wD4pBbl-mXlw&s"),
        Artist(id = 4, name = "Abida Parveen", genre = "Sufi", description = "Abida Parveen – One of Pakistan's most celebrated Sufi singers, known for her spiritual performances and mastery over traditional Sufi music.", image = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/Abida_Parveen.jpg/220px-Abida_Parveen.jpg"),
        Artist(id = 5, name = "Ali Zafar", genre = "Pop", description = "Ali Zafar – A multi-talented Pakistani artist, known for his work in pop music, acting, and his versatility in both Bollywood and Pakistani entertainment.", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXmsgEPWjQqPcZjqb1juV3LMx-u--5jLGytA&s"),
        Artist(id = 6, name = "Nusrat Fateh Ali Khan", genre = "Qawwali", description = "Nusrat Fateh Ali Khan – A world-renowned Qawwali singer whose powerful voice and performances revolutionized Sufi music internationally.", image = "https://feminisminindia.com/wp-content/uploads/2018/02/1163627-nus-1471339177-861-640x480.gif.png.webp"),
        Artist(id = 7, name = "Junoon", genre = "Rock", description = "Junoon – A legendary Pakistani rock band, famous for blending rock music with Sufi poetry and making an iconic impact on the Pakistani music scene.", image = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Junoon_Live_at_Channel_V_Awards.jpg/250px-Junoon_Live_at_Channel_V_Awards.jpg"),
        Artist(id = 8, name = "Bilal Saeed", genre = "hip hop", description = "Bilal Saeed – A rising star in the Pakistani hip-hop and R&B scene, known for his smooth blend of contemporary beats with urban lyrics.", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTSnZ-xLH-QQfsQ6yqF8AHiNnAKWGdq5OK0mw&s"),
        Artist(id = 9, name = "Talha Anjum", genre = "hip hop", description = "Talha Anjum – A prominent figure in Pakistan’s hip-hop and rap scene, part of the group 'Young Stunners,' known for his introspective and impactful lyrics.", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSzOdbnlzfLbF_kEAXQIe-3QhTDHB3FbCMYyg&s"),
        Artist(id = 10, name = "Farhan Saeed", genre = "Pop", description = "Farhan Saeed – A former member of the band Jal, Farhan Saeed is a well-known Pakistani pop singer, songwriter, and music producer.", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRSKEraSOlivWib9A8ZgEpclfeJTbPic7O5Iw&s"),
        Artist(id = 11, name = "Mehdi Hassan", genre = "Classical", description = "Mehdi Hassan – Known as the 'King of Ghazals,' Mehdi Hassan was a classical Pakistani singer renowned for his soulful ghazals and playback singing.", image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5eTk6BbyYj8U5EPGOrB7diz4XkSv6ILqnp1SAiA50OqePvxGX")
        )

    }

//    current artis k songs k liy view model main getallsongs main query chlani hy

}