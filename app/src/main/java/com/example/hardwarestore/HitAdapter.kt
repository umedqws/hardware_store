package com.example.hardwarestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hardwarestore.databinding.HitproductBinding
import com.example.hardwarestore.model.Products

class HitAdapter:ListAdapter<Products,HitAdapter.HitViewHolder>(HitDiffUtils()) {

    inner class HitViewHolder(itemView: View) : ViewHolder(itemView) {
      private val binding = HitproductBinding.bind(itemView)
        fun bind(products: Products) {
            products.image?.let { binding.image.setImageResource(it) }
            binding.name.text = products.nameProduct
            binding.description.text = products.descriptionProduct
            binding.price.text = "${products.price} sm"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitViewHolder {
        return HitViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.hitproduct, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HitViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HitDiffUtils:DiffUtil.ItemCallback<Products>(){
    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem == newItem
    }
}