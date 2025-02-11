package com.example.paktunes.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paktunes.R
import com.example.paktunes.adapter.RvAdapter
import com.example.paktunes.databinding.FragmentSongListBinding
import com.example.paktunes.ui.viewModel.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SongListFragment : Fragment(R.layout.fragment_song_list) {

//    private val viewModel: CategoryViewModel by viewModels()
    private val viewModel: MusicViewModel by activityViewModels()
//    private val musicViewModel: MusicViewModel by viewModels()
    private lateinit var recyclerViewAdapter :RvAdapter
    private lateinit var binding: FragmentSongListBinding
    private val arg : SongListFragmentArgs by navArgs()
    private val TAG = "SongListFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSongListBinding.bind(view)
        setUpRecyclerView()

        arg.let {
            binding.tvCategoryName.text = it.CategoryName
            Toast.makeText(requireContext(), it.CategoryName, Toast.LENGTH_SHORT).show()
//            viewModel.getSongsByCategoryName(categoryName = it.CategoryName)
            viewModel.songsLiveData.observe(viewLifecycleOwner){ list->
                Log.d(TAG, "songsLiveData: $list")
                if (!list.isNullOrEmpty()) {
                    // Once songs are available, filter by category
                    viewModel.getSongsByCategoryName(categoryName = it.CategoryName)
                }
            }
            viewModel.filteredSongsLiveData.observe(viewLifecycleOwner){ filteredList->
                recyclerViewAdapter.submitList(filteredList)
            }
        }
        recyclerViewAdapter.onCardClickListener{ position->
            Log.d(TAG, "Navigating to SongDetailFragment with position: $position")
            viewModel.setCurrentSongIndex(position)
            val action = SongListFragmentDirections.actionSongListFragmentToSongDetailFragment(position)
            findNavController().navigate(action)
        }
    }
    private fun setUpRecyclerView(){
        recyclerViewAdapter = RvAdapter()
        binding.recyclerView.apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }



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
//}
