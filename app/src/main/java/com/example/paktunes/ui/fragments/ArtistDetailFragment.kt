package com.example.paktunes.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paktunes.R
import com.example.paktunes.adapter.RvAdapter
import com.example.paktunes.databinding.ArtistDetailFragmentBinding
import com.example.paktunes.ui.viewModel.ArtistViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistDetailFragment(): Fragment(R.layout.artist_detail_fragment) {
    private lateinit var binding: ArtistDetailFragmentBinding
    private val viewModel: ArtistViewModel by viewModels()
    private val args: ArtistDetailFragmentArgs by navArgs()
    private lateinit var rvAdapter : RvAdapter

    private val TAG = "ArtistDetailFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ArtistDetailFragmentBinding.bind(view)
        setupRecyclerView()

        args.let {args ->
            binding.tvArtistName .text = args.artistName

            Toast.makeText(requireContext(), args.artistName, Toast.LENGTH_SHORT).show()

           viewModel.getSongsByArtistId(args.artistName)
            viewModel.filteredSongsLiveData.observe(viewLifecycleOwner){filteredSongs ->
                Log.d(TAG, "onViewCreated: filteredSongs $filteredSongs ")
                rvAdapter.submitList(filteredSongs)
            }
        }
    }
    private fun setupRecyclerView() {
        rvAdapter = RvAdapter()
        binding.recyclerView.apply {
            adapter = rvAdapter
//            layoutManager = LinearLayoutManager(context)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

}