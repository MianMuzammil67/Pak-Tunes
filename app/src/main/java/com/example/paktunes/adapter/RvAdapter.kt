package com.example.paktunes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paktunes.R
import com.example.paktunes.data.entities.Song

class RvAdapter(
    private val songs: List<Song>,
    private val onItemClick: (Int) -> Unit // Passes clicked item index
) :  RecyclerView.Adapter<RvAdapter.RvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return RvViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val song = songs[position]
        holder.bind(song)

        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int = songs.size

    class RvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val songTitle: TextView = itemView.findViewById(R.id.songTitle)
        private val songArtist: TextView = itemView.findViewById(R.id.songArtist)
        private val songThumbnail: ImageView = itemView.findViewById(R.id.songThumbnail)

        fun bind(song: Song) {
            songTitle.text = song.title
            songArtist.text = song.subtitle
            // Load thumbnail with Glide
            Glide.with(itemView.context)
                .load(song.imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(songThumbnail)
        }
    }
}