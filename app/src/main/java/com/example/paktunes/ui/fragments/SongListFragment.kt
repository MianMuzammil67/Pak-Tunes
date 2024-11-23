package com.example.paktunes.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paktunes.R
import com.example.paktunes.adapter.RvAdapter
import com.example.paktunes.databinding.FragmentSongListBinding
import com.example.paktunes.ui.viewModel.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongListFragment : Fragment(R.layout.fragment_song_list) {

    private val viewModel: MusicViewModel by viewModels()
    private lateinit var binding: FragmentSongListBinding
    private val TAG = "SongListFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSongListBinding.bind(view)

        //    purana but useful     ///////////////////////////////////////////////////////////////////////

//        val recyclerView: RecyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())

//        viewModel.songsLiveData.observe(viewLifecycleOwner) { songList ->
//            Log.e(TAG, songList.toString())
//
//            recyclerView.adapter = RvAdapter(songList) { selectedIndex ->
//
//                Toast.makeText(requireContext(), selectedIndex.toString(), Toast.LENGTH_LONG).show()
//
//                viewModel.setCurrentSongIndex(selectedIndex)
//                findNavController().navigate(R.id.action_songListFragment_to_songDetailFragment)
//            }
//        }
    }
}
