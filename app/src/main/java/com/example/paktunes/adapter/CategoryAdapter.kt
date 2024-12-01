package com.example.paktunes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paktunes.R
import com.example.paktunes.data.entities.Category
import com.example.paktunes.databinding.CategoryItemBinding

class CategoryAdapter(
    private val recyclerViewType: Int // 0 for RV1, 1 for RV2
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

     private var _itemClicked: ((String) -> Unit)? = null
    private lateinit var colors: List<Int>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        colors = listOf(
            ContextCompat.getColor(parent.context, R.color.olive_green),
            ContextCompat.getColor(parent.context, R.color.lavender_purple),
            ContextCompat.getColor(parent.context, R.color.coral_red),
            ContextCompat.getColor(parent.context, R.color.sky_blue)
        )
        return ViewHolder(
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = diffUtil.currentList[position]

        holder.binding.apply {
            val color = getColorForPosition(position)
            clParent.setBackgroundColor(color)
            tvCatName.text = category.name
        }
        holder.itemView.apply {
            setOnClickListener{
                _itemClicked?.let {
                    it(category.name)
                }
//                _itemClicked
            }
            Glide.with(context).load(category.image).placeholder(R.drawable.placeholder)
                .into(holder.binding.CategoryImage)

        }
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

    fun onCardClickListener(listener:(String)-> Unit){
        _itemClicked = listener
    }

    private fun getColorForPosition(position: Int): Int {
        return if (recyclerViewType == 0) {
            // RecyclerView 1 uses the color list normally
            colors[position % colors.size]
        } else {
            // RecyclerView 2 starts with a different color order
            colors[(position + 2) % colors.size] // Shift the color list by 2
        }
    }


    class ViewHolder(var binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root)


}