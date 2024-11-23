package com.example.paktunes.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paktunes.R
import com.example.paktunes.adapter.ArtistAdapter
import com.example.paktunes.adapter.CategoryAdapter
import com.example.paktunes.databinding.FragmentHomeBinding
import com.example.paktunes.ui.viewModel.ArtistViewModel
import com.example.paktunes.ui.viewModel.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment(): Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var musicCategoryAdapter : CategoryAdapter
    private lateinit var podcastCategoryAdapter : CategoryAdapter
    private lateinit var artistAdapter : ArtistAdapter
    private val musicViewModel: MusicViewModel by viewModels()
    private val artistViewModel: ArtistViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        setupMusicCategoryRecyclerView()
        setupPodcastCategoryRecyclerView()
        setupArtistRecyclerView()


        // Observe Music Categories LiveData
        musicViewModel.musicCategories.observe(viewLifecycleOwner) { categories ->
            // Update Music Category RecyclerView
            musicCategoryAdapter.submitList(categories)
        }

        // Observe Podcast Categories LiveData
        musicViewModel.podcastCategories.observe(viewLifecycleOwner) { categories ->
            Toast.makeText(requireContext(), categories[0].id.toString(), Toast.LENGTH_SHORT).show()

            // Update Podcast Category RecyclerView
            podcastCategoryAdapter.submitList(categories)
        }
        artistViewModel.artistLiveData.observe(viewLifecycleOwner){ artists ->
            artistAdapter.submitList(artists)
        }

    }
    private fun setupArtistRecyclerView(){
        artistAdapter = ArtistAdapter()
        binding.rvPopularArtist.apply {
            adapter = artistAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) // Horizontal layout for Podcast
        }
    }

    private fun setupMusicCategoryRecyclerView() {
        musicCategoryAdapter = CategoryAdapter()
        // Same adapter for both categories
        binding.rvMusicCategories.apply {
            adapter = musicCategoryAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) // Horizontal layout for Music
        }
    }

    private fun setupPodcastCategoryRecyclerView() {
        podcastCategoryAdapter = CategoryAdapter()
        // Same adapter for both categories
        binding.rvPodcastCategories.apply {
            adapter = podcastCategoryAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) // Horizontal layout for Podcast
        }
    }

}