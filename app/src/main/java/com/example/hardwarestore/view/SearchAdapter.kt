package com.example.hardwarestore.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hardwarestore.R
import com.example.hardwarestore.databinding.SearchBinding
import com.example.hardwarestore.model.Products

class SearchAdapter:ListAdapter<Products, SearchAdapter.SeacrhViewHolder>(HitDiffUtils()) {
    var onClick:((Products)->Unit)?=null
    inner class SeacrhViewHolder(itemView: View) : ViewHolder(itemView) {


        private val binding = SearchBinding.bind(itemView)
        fun bind(products: Products) {
            products.image?.let { binding.image.setImageResource(it) }
            binding.name.text = products.nameProduct
            binding.description.text = products.descriptionProduct
            binding.price.text = "${products.price} tjs"

            binding.aboutText.setOnClickListener {
                onClick?.invoke(getItem(adapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeacrhViewHolder {
        return SeacrhViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SeacrhViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class SearchDiffUtils:DiffUtil.ItemCallback<Products>(){
    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem == newItem
    }
}