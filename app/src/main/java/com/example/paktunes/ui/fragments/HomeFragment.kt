package com.example.paktunes.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paktunes.R
import com.example.paktunes.adapter.ArtistAdapter
import com.example.paktunes.adapter.CategoryAdapter
import com.example.paktunes.adapter.PopularMusicAdapter
import com.example.paktunes.databinding.FragmentHomeBinding
import com.example.paktunes.ui.viewModel.ArtistViewModel
import com.example.paktunes.ui.viewModel.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var musicCategoryAdapter : CategoryAdapter
    private lateinit var podcastCategoryAdapter : CategoryAdapter
    private lateinit var artistAdapter : ArtistAdapter
    private lateinit var popularMusicAdapter : PopularMusicAdapter
    private val musicViewModel: MusicViewModel by activityViewModels()
    private val artistViewModel: ArtistViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        setupMusicCategoryRecyclerView()
        setupPodcastCategoryRecyclerView()
        setupArtistRecyclerView()
        setupPopularSongsRecyclerView()

        //fetching data from viewModel
        musicViewModel.musicCategories.observe(viewLifecycleOwner) { categories ->
            musicCategoryAdapter.submitList(categories)
        }
        musicViewModel.podcastCategories.observe(viewLifecycleOwner) { categories ->
            podcastCategoryAdapter.submitList(categories)
        }
        artistViewModel.artistLiveData.observe(viewLifecycleOwner){ artists ->
            artistAdapter.submitList(artists)
        }
        musicViewModel.popularSongs.observe(viewLifecycleOwner) { popularSongs ->
            popularMusicAdapter.submitList(popularSongs)
        }

//        setting click listeners

        musicCategoryAdapter.onCardClickListener {categoryName->
            val action = HomeFragmentDirections.actionHomeFragmentToSongListFragment(categoryName)
            findNavController().navigate(action)
        }
        podcastCategoryAdapter.onCardClickListener { categoryName->
            val action = HomeFragmentDirections.actionHomeFragmentToSongListFragment(categoryName)
            findNavController().navigate(action)
        }

        popularMusicAdapter.onCardClickListener { position->
            val action = HomeFragmentDirections.actionHomeFragmentToSongDetailFragment(position)
            findNavController().navigate(action)
        }
        artistAdapter.onCardClickListener { artistName->
            val action = HomeFragmentDirections.actionHomeFragmentToArtistDetailFragment(artistName)
            findNavController().navigate(action)
        }

    }
    private fun setupArtistRecyclerView(){
        artistAdapter = ArtistAdapter()
        binding.rvPopularArtist.apply {
            adapter = artistAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
    private fun setupPopularSongsRecyclerView(){
        popularMusicAdapter = PopularMusicAdapter()
        binding.rvPopularSongs.apply {
            adapter = popularMusicAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
    private fun setupMusicCategoryRecyclerView() {
        musicCategoryAdapter = CategoryAdapter(0)
        // Same adapter for both categories
        binding.rvMusicCategories.apply {
            adapter = musicCategoryAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
    private fun setupPodcastCategoryRecyclerView() {
        podcastCategoryAdapter = CategoryAdapter(1)
        // Same adapter for both categories
        binding.rvPodcastCategories.apply {
            adapter = podcastCategoryAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

}