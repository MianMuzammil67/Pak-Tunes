package com.example.paktunes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paktunes.R
import com.example.paktunes.data.entities.Artist
import com.example.paktunes.databinding.ArtistItemBinding

class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    private var _itemClicked: ((Artist) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ArtistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artist = diffUtil.currentList[position]
        holder.binding.apply {
            tvArtistName.text = artist.name
        }
        holder.itemView.apply {
            setOnClickListener{
                _itemClicked?.let {
                    it(artist)
                }
                _itemClicked
            }

        Glide.with(context).load(artist.image)
            .placeholder(R.drawable.artist_placeholder)
            .into(holder.binding.ivArtistPhoto)
        }
    }
    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem == newItem
        }
    }
    private val diffUtil = AsyncListDiffer(this, diffCallBack)

    fun submitList(list: List<Artist>) {
        diffUtil.submitList(list)
    }

    fun onCardClickListener (listener: (Artist)-> Unit){
            _itemClicked = listener
    }

    class ViewHolder(var binding: ArtistItemBinding) :
        RecyclerView.ViewHolder(binding.root)



}