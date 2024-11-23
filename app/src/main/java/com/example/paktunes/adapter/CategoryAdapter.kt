package com.example.paktunes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paktunes.R
import com.example.paktunes.data.entities.Category
import com.example.paktunes.databinding.CategoryItemBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = diffUtil.currentList[position]
        holder.binding.apply {
            tvCatName.text = category.name
        }
//        holder.itemView.apply {
            Glide.with(holder.itemView.context).load(category.image).placeholder(R.drawable.placeholder)
                .into(holder.binding.CategoryImage)

//        }
    }


    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
        private val diffUtil = AsyncListDiffer(this, diffCallBack)

    fun submitList(list: List<Category>) {
        diffUtil.submitList(list)
    }

    class ViewHolder(var binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)


}