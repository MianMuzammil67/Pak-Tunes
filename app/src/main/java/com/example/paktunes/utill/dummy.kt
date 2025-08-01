//package com.example.paktunes.ui.fragments
//
//import android.content.ComponentName
//import android.net.Uri
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import android.os.SystemClock
//import android.util.Log
//import android.view.View
//import android.widget.SeekBar
//import android.widget.Toast
//import androidx.core.view.isVisible
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.media3.common.MediaItem
//import androidx.media3.common.MediaMetadata
//import androidx.media3.common.Player
//import androidx.media3.session.MediaController
//import androidx.media3.session.SessionToken
//import androidx.navigation.fragment.navArgs
//import com.example.paktunes.R
//import com.example.paktunes.data.entities.Song
//import com.example.paktunes.databinding.FragmentSongDetailBinding
//import com.example.paktunes.exoplayer.service.MusicService
//import com.example.paktunes.ui.viewModel.CategoryViewModel
//import com.example.paktunes.ui.viewModel.MusicViewModel
//import com.google.common.util.concurrent.ListenableFuture
//import com.google.common.util.concurrent.MoreExecutors
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class SongDetailFragment : Fragment(R.layout.fragment_song_detail) {
//
//    private lateinit var binding: FragmentSongDetailBinding
//    private var currSongIndex: Song? = null
//    private lateinit var songList: List<Song>
//    private val args: SongDetailFragmentArgs by navArgs()
//
//    private val viewModel: MusicViewModel by viewModels()
//    private val catViewModel: CategoryViewModel by viewModels()
//    private val TAG = "SongDetailFragment"
//
//    var duration: Int = 0
//    private lateinit var controller: MediaController
//    private var mediaControllerFuture: ListenableFuture<MediaController>? = null
//
//    // Elapsed Time
//    private var stopwatchStartTime: Long = 0
//    private var elapsedTime: Long = 0
//    private var isStopwatchRunning: Boolean = false
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentSongDetailBinding.bind(view)
//        val position = args.position
////        Log.d(TAG, "Received position: $position")
//        initializeMediaController()
//        setupUIControls()
//
//        viewModel.getSongsByCategoryName(categoryName = "pop")
//
//        viewModel.filteredSongsLiveData.observe(viewLifecycleOwner){ list->
//            initializeMediaController()
//            setupUIControls()
//
//            songList = list
//            Log.d(TAG, "Received song index: ${list.toString()}")
//            currSongIndex = list[position]
//            Log.d(TAG, "Received song index: ${currSongIndex.toString()}")
//            currSongIndex?.let { playMedia(it) }
//
//        }
////        viewModel.setCurrentSongIndex(position)
//
////        viewModel.currentSongIndex.observe(viewLifecycleOwner) { song ->
////            Log.d(TAG, "Received song index: $song")
////        }
//
//
////        viewModel.songsLiveData.observe(viewLifecycleOwner) { songs ->
////            songList = songs
////            Log.d(TAG,"Received songs : $songs")
////            if (songs.isNotEmpty() && args.position >= 0 && args.position < songs.size) {
////                currSong = songs[args.position]  // Use position to fetch the song
//////                playMedia(currSong  )  // Play the song at the provided position
////                currSong?.let { playMedia(it) } ?: Log.e(TAG, "currSong is null, cannot play media.")
////
////            }
////        }
//
////        viewModel.getCurrentSong().observe(viewLifecycleOwner) { song ->
////            if (song != null) {
////                Log.d(TAG, "currSong is : $song")
////        playMedia(it)
////            } else {
////                Log.d(TAG, "currSong is empty")
////            }
////            Toast.makeText(requireContext(), song?.toString() ?: "No song available", Toast.LENGTH_SHORT).show()
////        }
//
//
////        viewModel.currentSongIndex.observe(viewLifecycleOwner) { index ->
////            if (index != null && index in 0 until (viewModel.songsLiveData.value?.size ?: 0)) {
////                // Play the song at the current index
////                val currentSong = viewModel.songsLiveData.value?.get(index)
////                if (currentSong != null) {
////                    playMedia(currentSong)
////                }
////            }
////        }
//
////        viewModel.currentSong.observe(viewLifecycleOwner) { songs ->
////            if (songs != null) {
////                currSong = songs
////            } else {
////                Log.d(TAG, "Current song is null.")
////                Toast.makeText(requireContext(), "Current song is null", Toast.LENGTH_SHORT).show()
////            }
////        }
//
//
//        binding.btnNextTrack.setOnClickListener {
//            viewModel.playNextSong()
//        }
//        binding.btnPrevTrack.setOnClickListener {
//            viewModel.playPreviousSong()
//        }
//
////        initializeMediaController()
////        setupUIControls()
//    }
//
//    private fun initializeMediaController() {
//        val sessionToken = SessionToken(
//            requireContext(),
//            ComponentName(requireContext(), MusicService::class.java)
//        )
//        mediaControllerFuture = MediaController.Builder(requireContext(), sessionToken).buildAsync()
//        mediaControllerFuture?.apply {
//            addListener({
//                controller = get()
//                updateUIWithMediaController(controller)
//                // Ensure media is played appropriately based on state
//                log("INITIAL STATE = ${controller.playbackState}")
//                handlePlaybackBasedOnState()
//            }, MoreExecutors.directExecutor())
//        }
//    }
//
//    private fun handlePlaybackBasedOnState() {
//        if (controller.playbackState == Player.STATE_IDLE || controller.playbackState == Player.STATE_ENDED) {
////            currSong?.let { playMedia(it) } ?: Log.e(TAG, "currSong is null, cannot play media.")
//
////            playMedia(currSong)
//        } else if (controller.playbackState == Player.STATE_READY || controller.playbackState == Player.STATE_BUFFERING) {
//            updateUIWithPlayback()
//        }
//    }
//
//    private fun updateUIWithPlayback() {
//        hideBuffering()
//        updatePlayPauseButton(controller.playWhenReady)
//        if (controller.playWhenReady) {
//            val currentPosition = controller.currentPosition.toInt() / 1000
//            binding.seekbar.progress = currentPosition
//            binding.time.text = getTimeString(currentPosition)
//            binding.duration.text = getTimeString(controller.duration.toInt() / 1000)
//        }
//    }
//
//    private fun playMedia(song: Song) {
//        Log.d(TAG, "inside playMedia: ${song.title}")
//        binding.tvSongTitle.text = song.title
//        val mediaItem = MediaItem.Builder()
//            .setMediaId(song.songUrl)
////                        .setMediaId("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3")
//            .setMediaMetadata(
//                MediaMetadata.Builder()
//                    .setFolderType(MediaMetadata.FOLDER_TYPE_ALBUMS)
//                    .setArtworkUri(Uri.parse(song.imageUrl))
////                    .setArtworkUri(Uri.parse("https://i.pinimg.com/736x/4b/02/1f/4b021f002b90ab163ef41aaaaa17c7a4.jpg"))
//                    .setAlbumTitle(song.title)
//                    .setDisplayTitle(song.title)
//                    .setSubtitle(song.artistName)
//                    .build()
//            ).build()
//
//        controller.setMediaItem(mediaItem)
//        controller.prepare()
//        controller.play()
//    }
//
//    private fun updateUIWithMediaController(controller: MediaController) {
//        controller.addListener(object : Player.Listener {
//            override fun onPositionDiscontinuity(
//                oldPosition: Player.PositionInfo, newPosition: Player.PositionInfo, reason: Int
//            ) {
//                val currentPosition = controller.currentPosition.toInt() / 1000
//                binding.seekbar.progress = currentPosition
//                binding.time.text = getTimeString(currentPosition)
//                binding.duration.text = getTimeString(controller.duration.toInt() / 1000)
//            }
//
//            override fun onPlaybackStateChanged(playbackState: Int) {
//                when (playbackState) {
//                    Player.STATE_BUFFERING -> {
//                        showBuffering()
//                        pauseStopwatch()
//                    }
//
//                    Player.STATE_READY -> {
//                        hideBuffering()
//                        updatePlayPauseButton(controller.playWhenReady)
//                        if (controller.playWhenReady) {
//                            startStopwatch()
//                        }
//                    }
//
//                    Player.STATE_ENDED -> handlePlaybackEnded()
//                    Player.STATE_IDLE -> {
//                        pauseStopwatch()
//                    }
//                }
//            }
//
//            override fun onIsPlayingChanged(isPlaying: Boolean) {
//                updatePlayPauseButton(isPlaying)
//
//                if (isPlaying) {
//                    startStopwatch()
//                } else {
//                    pauseStopwatch()
//                }
//
//                duration = controller.duration.toInt() / 1000
//                binding.seekbar.max = duration
//                binding.time.text = "0:00"
//                binding.duration.text = getTimeString(duration)
//            }
//        })
//
//        updatePlayPauseButton(controller.playWhenReady)
//
//        val handler = Handler(Looper.getMainLooper())
//        handler.post(object : Runnable {
//            override fun run() {
//                val currentposition = controller.currentPosition.toInt() / 1000
//                binding.seekbar.progress = currentposition
//                binding.time.text = getTimeString(currentposition)
//                binding.duration.text = getTimeString(duration)
//                handler.postDelayed(this, 1000)
//            }
//        })
//    }
//
//    private fun setupUIControls() {
//        binding.btnPlayPause.setOnClickListener {
//            if (controller.playWhenReady) {
//                controller.pause()
//            } else {
//                controller.play()
//            }
//        }
//
//        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                if (fromUser) controller.seekTo(progress.toLong() * 1000)
//                binding.time.text = getTimeString(progress)
//                binding.duration.text = getTimeString(duration)
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//                pauseStopwatch()
//            }
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//                if (controller.playWhenReady) {
//                    startStopwatch()
//                }
//            }
//        })
//    }
//
//    private fun startStopwatch() {
//        if (!isStopwatchRunning) {
//            stopwatchStartTime = SystemClock.elapsedRealtime() - elapsedTime
//            isStopwatchRunning = true
//        }
//    }
//
//    private fun pauseStopwatch() {
//        if (isStopwatchRunning) {
//            elapsedTime = SystemClock.elapsedRealtime() - stopwatchStartTime
//            isStopwatchRunning = false
//        }
//    }
//
//    private fun reportListenTime() {
//        if (isStopwatchRunning) {
//            elapsedTime = SystemClock.elapsedRealtime() - stopwatchStartTime
//        }
//        // Convert milliseconds to seconds
//        val listenedTimeInSeconds = elapsedTime / 1000
//
//        Toast.makeText(
//            requireContext(),
//            "Total listened time: $listenedTimeInSeconds seconds",
//            Toast.LENGTH_SHORT
//        ).show()
//        log("Total listened time: $listenedTimeInSeconds seconds")
//    }
//
//    private fun updatePlayPauseButton(isPlaying: Boolean) {
//        binding.btnPlayPause.setImageResource(if (isPlaying) R.drawable.pause else R.drawable.play)
//    }
//
//    private fun getTimeString(seconds: Int): String {
//        val mins = seconds / 60
//        val secs = seconds % 60
//        return String.format("%02d:%02d", mins, secs)
//    }
//
//    private fun showBuffering() {
//        binding.progressBar.show()
//    }
//
//    private fun hideBuffering() {
//        binding.progressBar.hide()
//    }
//
//    private fun handlePlaybackEnded() {
//        binding.btnPlayPause.setImageResource(R.drawable.play)
//        binding.seekbar.progress = 0
//        binding.time.text = getTimeString(duration)
//        binding.duration.text = getTimeString(duration)
//        binding.progressBar.visibility = View.GONE
//        pauseStopwatch()
//        reportListenTime()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mediaControllerFuture?.let {
//            MediaController.releaseFuture(it)
//        }
//    }
//
//    private fun log(message: String) {
//        Log.e("=====[DebzMediaPlayer]=====", message)
//    }
//
//
//    companion object {
//        fun View.hide() {
//            isVisible = false
//        }
//
//        fun View.show() {
//            isVisible = true
//        }
//
//        const val CURRENT_POSITION = "CURRENT_POSITION"
//    }
//
//
//}
////////////////////////////////////////////  usefull      \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//
////class SongDetailFragment : Fragment(R.layout.fragment_song_detail) {
////
////    private val viewModel: MusicViewModel by viewModels()
////    private lateinit var binding: FragmentSongDetailBinding
////
////    private var musicService: MusicService? = null
////    private var isBound = false
////
////    private val serviceConnection = object : ServiceConnection {
////        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
////            val binder = service as MusicService.MusicServiceBinder
////            musicService = binder.getService()
////            isBound = true
////
////            // Play the current song when connected
////            viewModel.currentSongIndex.value?.let { index ->
////                val song = viewModel.songs.value?.get(index)
////                song?.let {
////                    musicService?.playSong(it)
////                    updateUI(it)
////                }
////            }
////        }
////
////        override fun onServiceDisconnected(name: ComponentName?) {
////            musicService = null
////            isBound = false
////        }
////    }
////
////    override fun onStart() {
////        super.onStart()
////        // Bind to MusicService
////        Intent(requireContext(), MusicService::class.java).also { intent ->
////            requireContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
////        }
////    }
////
////    override fun onStop() {
////        super.onStop()
////        // Unbind from the service
////        if (isBound) {
////            requireContext().unbindService(serviceConnection)
////            isBound = false
////        }
////    }
////
////    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
////        super.onViewCreated(view, savedInstanceState)
////        binding = FragmentSongDetailBinding.bind(view)
////
////        // Observe current song changes
////        viewModel.currentSongIndex.observe(viewLifecycleOwner) { index ->
////            val song = viewModel.songs.value?.get(index)
////            song?.let {
////                musicService?.playSong(it)
////                updateUI(it)
////            }
////        }
////
////        // Play/Pause Button
////        binding.playButton.setOnClickListener {
////            if (musicService?.isPlaying() == true) {
////                musicService?.pause()
////                binding.playButton.setImageResource(R.drawable.ic_play)
////            } else {
////                musicService?.play()
////                binding.playButton.setImageResource(R.drawable.icon_pause)
////            }
////        }
////
////        // Next Button
////        binding.nextButton.setOnClickListener {
////            viewModel.playNextSong()
////        }
////
////        // Previous Button
////        binding.prevButton.setOnClickListener {
////            viewModel.playPreviousSong()
////        }
////    }
////
////    private fun updateUI(song: Song) {
////        binding.songTitle.text = song.title
////        binding.songArtist.text = song.subtitle
////        Glide.with(this)
////            .load(song.imageUrl)
////            .placeholder(R.drawable.placeholder)
////            .into(binding.thumbnailImageView)
////    }
////}
