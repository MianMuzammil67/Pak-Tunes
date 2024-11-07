package com.example.paktunes.ui

import androidx.fragment.app.Fragment
import com.example.paktunes.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicPlayerFragment : Fragment(R.layout.fragment_song) {
//    private lateinit var musicViewModel: MusicViewModel
//    private var musicService: MusicService? = null
//    private var isServiceBound = false
//    private lateinit var serviceConnection: ServiceConnection
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        musicViewModel = ViewModelProvider(this)[MusicViewModel::class.java]
//
//        serviceConnection = object : ServiceConnection {
//            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
//                val binder = service as MusicService.MusicServiceBinder
//                musicService = binder.getService()
//                isServiceBound = true
//            }
//
//            override fun onServiceDisconnected(name: ComponentName?) {
//                musicService = null
//                isServiceBound = false
//            }
//        }
//
//        bindService()
//
//
////        musicViewModel.musicUrl.observe(viewLifecycleOwner) { musicList ->
////            val mediaItems = musicList.map { music ->
////                MediaItem.fromUri(music.toString())
////            }
////            musicService?.playMusic(mediaItems)
////        }
//    }
//
//    private fun bindService() {
//        val serviceIntent = Intent(requireContext(), MusicService::class.java)
//        requireContext().bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
//    }
//
//    private fun unbindService() {
//        if (isServiceBound) {
//            requireContext().unbindService(serviceConnection)
//            isServiceBound = false
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        unbindService()
//    }


}