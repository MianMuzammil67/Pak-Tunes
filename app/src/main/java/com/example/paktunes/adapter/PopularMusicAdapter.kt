package com.example.paktunes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paktunes.R
import com.example.paktunes.data.entities.Song
import com.example.paktunes.databinding.PopularItemsBinding

class PopularMusicAdapter : RecyclerView.Adapter<PopularMusicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            PopularItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = diffUtil.currentList[position]
        holder.binding.apply {
            tvSongTitle.text = song.title
            tvSongArtist.text = song.artistName

        }
        Glide.with(holder.itemView.context).load(song.imageUrl)
            .placeholder(R.drawable.placeholder)
            .into(holder.binding.songThumbnail)
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }
    }
    private val diffUtil = AsyncListDiffer(this, diffCallBack)

    fun submitList(list: List<Song>) {
        diffUtil.submitList(list)
    }

    class ViewHolder(var binding: PopularItemsBinding) :
        RecyclerView.ViewHolder(binding.root)
}