package com.example.paktunes.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paktunes.R
import com.example.paktunes.data.entities.Song
import com.example.paktunes.databinding.ItemSongBinding

class RvAdapter(
    //    private val songs: List<Song>,
    //    private val onItemClick: (Int) -> Unit // Passes clicked item index
) : RecyclerView.Adapter<RvAdapter.RvViewHolder>() {
    private val TAG = "RVMain"

    private var _onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        Log.d(TAG, " onCreateViewHolder called ")

        return RvViewHolder(
            ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        Log.d(TAG, " onBindViewHolder called ")
        val currentSongList = diffUtil.currentList[position]
        Log.d(TAG, " currentSongList : ${currentSongList.title}?: currentSongList is empty ")

        //        holder.bind(song)
        holder.binding.apply {
            songTitle.text = currentSongList.title
            Log.d(TAG, "setting data to views : ${currentSongList.title} ")
            songArtist.text = currentSongList.artistName
        }
        holder.itemView.apply {
            Glide.with(context)
                .load(currentSongList.imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(holder.binding.songThumbnail)
            //            setOnClickListener {
            //                _onItemClick?.let { it1 -> it1(position) }
            //            }
        }
    }

    override fun getItemCount(): Int {
        Log.d(TAG, " getItemCount called list size : ${diffUtil.currentList.size} ")
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

    fun submitList(songList: List<Song>) {
        Log.d(TAG, "songListFromFragment b $songList ")
        diffUtil.submitList(songList)
        Log.d(TAG, "songListFromFragment a ${diffUtil.currentList} ")
    }

    fun onCardClickListener(listener: (Int) -> Unit) {
        _onItemClick = listener
    }

    class RvViewHolder(var binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root)
}