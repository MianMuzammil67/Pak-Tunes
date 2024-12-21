package com.example.paktunes.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.paktunes.R
import com.example.paktunes.adapter.RvAdapter
import com.example.paktunes.databinding.ArtistDetailFragmentBinding
import com.example.paktunes.ui.viewModel.ArtistViewModel
import com.example.paktunes.ui.viewModel.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistDetailFragment(): Fragment(R.layout.artist_detail_fragment) {
    private lateinit var binding: ArtistDetailFragmentBinding
    private val viewModel: ArtistViewModel by viewModels()
    private val musicViewModel: MusicViewModel by viewModels()
    private val args: ArtistDetailFragmentArgs by navArgs()
    private lateinit var rvAdapter : RvAdapter

    private val TAG = "ArtistDetailFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ArtistDetailFragmentBinding.bind(view)
        setupRecyclerView()

        args.let {args ->
            val artistData = args.artistData
            binding.tvArtistName .text = artistData.name
            binding.tvDescription.text = artistData.description
            Glide.with(requireContext()).load(artistData.image).placeholder(R.drawable.placeholder).into(binding.ivArtistPhoto)
            Toast.makeText(requireContext(), artistData.name, Toast.LENGTH_SHORT).show()
           viewModel.getSongsByArtistId(artistData.name)
        }
        viewModel.filteredSongsLiveData.observe(viewLifecycleOwner){filteredSongs ->
            Log.d(TAG, "onViewCreated: filteredSongs $filteredSongs ")
            rvAdapter.submitList(filteredSongs)
        }
        rvAdapter.onCardClickListener { position->
            findNavController().navigate(ArtistDetailFragmentDirections.actionArtistDetailFragmentToSongDetailFragment(position))
            musicViewModel.setCurrentSongIndex(position)
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