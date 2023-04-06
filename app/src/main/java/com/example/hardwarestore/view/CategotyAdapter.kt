package com.example.hardwarestore.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hardwarestore.R
import com.example.hardwarestore.databinding.CategoryBinding
import com.example.hardwarestore.model.Categories

class CategoryAdapter:ListAdapter<Categories, CategoryAdapter.CategoryViewHolder>(CategoryDiffUtils()) {
    var onClickItem:((Categories)->Unit)?=null
    inner class CategoryViewHolder(itemView: View):ViewHolder(itemView){
       private val binding = CategoryBinding.bind(itemView)
        init {
            itemView.setOnClickListener {
                onClickItem?.invoke(getItem(adapterPosition))
            }
        }


        fun bind(categories: Categories){
            binding.category.text = categories.nameCategory
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category,parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CategoryDiffUtils:DiffUtil.ItemCallback<Categories>(){
    override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
        return oldItem == newItem
    }

}

