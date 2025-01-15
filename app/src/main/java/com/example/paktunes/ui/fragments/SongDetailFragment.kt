package com.example.paktunes.ui.fragments

import android.content.ComponentName
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import androidx.navigation.fragment.navArgs
import com.example.paktunes.R
import com.example.paktunes.data.entities.Song
import com.example.paktunes.databinding.FragmentSongDetailBinding
import com.example.paktunes.exoplayer.service.MusicService
import com.example.paktunes.ui.viewModel.CategoryViewModel
import com.example.paktunes.ui.viewModel.MusicViewModel
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.MoreExecutors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongDetailFragment : Fragment(R.layout.fragment_song_detail) {

    private lateinit var binding: FragmentSongDetailBinding
    private var currSong: Song? = null
    private lateinit var songList: List<Song>
    private val args: SongDetailFragmentArgs by navArgs()

    private val viewModel: MusicViewModel by viewModels()
    private val catViewModel: CategoryViewModel by viewModels()
    private val TAG = "SongDetailFragment"

    var duration: Int = 0
    private lateinit var controller: MediaController
    private var mediaControllerFuture: ListenableFuture<MediaController>? = null

    // Elapsed Time
    private var stopwatchStartTime: Long = 0
    private var elapsedTime: Long = 0
    private var isStopwatchRunning: Boolean = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSongDetailBinding.bind(view)
        val position = args.position
//        Log.d(TAG, "Received position: $position")

        initializeMediaController()
        setupUIControls()

//        viewModel.getSongsByCategoryName(categoryName = "pop")

        viewModel.filteredSongsLiveData.observe(viewLifecycleOwner) { list ->
            songList = list
            Log.d(TAG, "Received song index: ${list.toString()}")
            currSong = list[position]
            Log.d(TAG, "Received song index: ${currSong.toString()}")
//            currSongIndex?.let { playMedia(it) }
        }

        viewModel.currentSongIndex.observe(viewLifecycleOwner){
            Log.d(TAG, " currentSongIndex : $it")
        }

//        viewModel.setCurrentSongIndex(position)

//        viewModel.currentSongIndex.observe(viewLifecycleOwner) { song ->
//            Log.d(TAG, "Received song index: $song")
//        }
//        viewModel.getCurrentSongg()

        viewModel.currentSongLiveData.observe(viewLifecycleOwner) { song ->
            Toast.makeText(requireContext(), "Current song is not null $song", Toast.LENGTH_SHORT)
                .show()
            if (song != null) {
                currSong = song
                Toast.makeText(
                    requireContext(),
                    "Current song is not null $currSong",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Log.d(TAG, "Current song is null.")
                Toast.makeText(requireContext(), "Current song is null", Toast.LENGTH_SHORT).show()
            }
        }


        binding.tvSongTitle.text = currSong?.title
        binding.tvArtistName.text = currSong?.artistName
        binding.btnNextTrack.setOnClickListener {
            viewModel.playNextSong()
        }
        binding.btnPrevTrack.setOnClickListener {
            viewModel.playPreviousSong()
        }


    }

    private fun initializeMediaController() {
        val sessionToken = SessionToken(
            requireContext(),
            ComponentName(requireContext(), MusicService::class.java)
        )
        mediaControllerFuture = MediaController.Builder(requireContext(), sessionToken).buildAsync()
        mediaControllerFuture?.apply {
            addListener(Runnable {
                controller = get()
                updateUIWithMediaController(controller)
                // Ensure media is played appropriately based on state
                log("INITIAL STATE = ${controller.playbackState}")
                handlePlaybackBasedOnState()
            }, MoreExecutors.directExecutor())
        }
    }

    private fun handlePlaybackBasedOnState() {
        if (controller.playbackState == Player.STATE_IDLE || controller.playbackState == Player.STATE_ENDED) {
            playMedia()
        } else if (controller.playbackState == Player.STATE_READY || controller.playbackState == Player.STATE_BUFFERING) {
            updateUIWithPlayback()
        }
    }

    private fun updateUIWithPlayback() {
        hideBuffering()
        updatePlayPauseButton(controller.playWhenReady)
        if (controller.playWhenReady) {
            val currentposition = controller.currentPosition.toInt() / 1000
            binding.seekbar.progress = currentposition
            binding.time.text = getTimeString(currentposition)
            binding.duration.text = getTimeString(controller.duration.toInt() / 1000)
        }
    }

    private fun playMedia() {
        val mediaItem = currSong?.let {
            MediaItem.Builder()
                //            .setMediaId("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3")
//                .setMediaId("https://songs.apniisp.com/Atif%20Aslam%20-%20Meri%20Kahani/09%20-%20Kaun%20Tha%20-%20Kapkapi%20(Apniisp.Com).mp3")
                .setMediaId(it.songUrl)
                .setMediaMetadata(
                    MediaMetadata.Builder()
                        .setFolderType(MediaMetadata.FOLDER_TYPE_ALBUMS)
//                        .setArtworkUri(Uri.parse("https://www.bbc.co.uk/staticarchive/50cf0c0b92f670f72bcb5a7d99ba93e94356786e.jpg"))
                        .setArtworkUri(Uri.parse(it.imageUrl))
                        .setAlbumTitle("SoundHelix")
                        .setDisplayTitle(it.title)
                        .build()
                ).build()
        }
        if (mediaItem != null) {
            controller.setMediaItem(mediaItem)
        }
        controller.prepare()
        controller.play()
    }

    private fun updateUIWithMediaController(controller: MediaController) {
        controller.addListener(object : Player.Listener {

            override fun onPositionDiscontinuity(
                oldPosition: Player.PositionInfo, newPosition: Player.PositionInfo, reason: Int
            ) {
                val currentposition = controller.currentPosition.toInt() / 1000
                binding.seekbar.progress = currentposition
                binding.time.text = getTimeString(currentposition)
                binding.duration.text = getTimeString(controller.duration.toInt() / 1000)
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        showBuffering()
                        pauseStopwatch()
                    }

                    Player.STATE_READY -> {
                        hideBuffering()
                        updatePlayPauseButton(controller.playWhenReady)
                        if (controller.playWhenReady) {
                            startStopwatch()
                        }
                    }

                    Player.STATE_ENDED -> handlePlaybackEnded()
                    Player.STATE_IDLE -> {
                        pauseStopwatch()
                    }
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                updatePlayPauseButton(isPlaying)

                if (isPlaying) {
                    startStopwatch()
                } else {
                    pauseStopwatch()
                }

                duration = controller.duration.toInt() / 1000
                binding.seekbar.max = duration
                binding.time.text = "0:00"
                binding.duration.text = getTimeString(duration)
            }
        })

        updatePlayPauseButton(controller.playWhenReady)

        val handler = Handler(Looper.getMainLooper())
        handler.post(object : Runnable {
            override fun run() {
                val currentposition = controller.currentPosition.toInt() / 1000
                binding.seekbar.progress = currentposition
                binding.time.text = getTimeString(currentposition)
                binding.duration.text = getTimeString(duration)
                handler.postDelayed(this, 1000)
            }
        })
    }

    private fun setupUIControls() {
        binding.btnPlayPause.setOnClickListener {
            if (controller.playWhenReady) {
                controller.pause()
            } else {
                controller.play()
            }
        }

        binding.seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) controller.seekTo(progress.toLong() * 1000)
                binding.time.text = getTimeString(progress)
                binding.duration.text = getTimeString(duration)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                pauseStopwatch()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (controller.playWhenReady) {
                    startStopwatch()
                }
            }
        })
    }

    private fun startStopwatch() {
        if (!isStopwatchRunning) {
            stopwatchStartTime = SystemClock.elapsedRealtime() - elapsedTime
            isStopwatchRunning = true
        }
    }

    private fun pauseStopwatch() {
        if (isStopwatchRunning) {
            elapsedTime = SystemClock.elapsedRealtime() - stopwatchStartTime
            isStopwatchRunning = false
        }
    }

    private fun reportListenTime() {
        if (isStopwatchRunning) {
            elapsedTime = SystemClock.elapsedRealtime() - stopwatchStartTime
        }
        // Convert milliseconds to seconds
        val listenedTimeInSeconds = elapsedTime / 1000

        Toast.makeText(
            requireContext(),
            "Total listened time: $listenedTimeInSeconds seconds",
            Toast.LENGTH_SHORT
        ).show()
        log("Total listened time: $listenedTimeInSeconds seconds")
    }

    private fun updatePlayPauseButton(isPlaying: Boolean) {
        binding.btnPlayPause.setImageResource(if (isPlaying) R.drawable.pause else R.drawable.play)
    }

    private fun getTimeString(seconds: Int): String {
        val mins = seconds / 60
        val secs = seconds % 60
        return String.format("%02d:%02d", mins, secs)
    }

    private fun showBuffering() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideBuffering() {
        binding.progressBar.visibility = View.GONE
    }

    private fun handlePlaybackEnded() {
        binding.btnPlayPause.setImageResource(R.drawable.play)
        binding.seekbar.progress = 0
        binding.time.text = getTimeString(duration)
        binding.duration.text = getTimeString(duration)
        binding.progressBar.visibility = View.GONE
        pauseStopwatch()
        reportListenTime()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaControllerFuture?.let {
            MediaController.releaseFuture(it)
        }
    }

    private fun log(message: String) {
        Log.e("=====[DebzMediaPlayer]=====", message)
    }


}