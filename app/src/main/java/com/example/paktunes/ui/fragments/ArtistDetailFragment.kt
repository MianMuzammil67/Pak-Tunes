package com.example.paktunes.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.paktunes.R
import com.example.paktunes.databinding.ArtistDetailFragmentBinding

class ArtistDetailFragment(): Fragment(R.layout.artist_detail_fragment) {
    private lateinit var binding: ArtistDetailFragmentBinding
    private val args: ArtistDetailFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.let {args ->
            Toast.makeText(requireContext(), args.artistName, Toast.LENGTH_SHORT).show()
        }
    }
}