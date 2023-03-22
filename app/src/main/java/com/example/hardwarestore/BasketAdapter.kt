package com.example.hardwarestore

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hardwarestore.databinding.BasketBinding
import com.example.hardwarestore.model.Basket

class BasketAdapter:ListAdapter<Basket,BasketAdapter.BasketViewHolder>(BasketDiffUtil()) {
    inner class BasketViewHolder(itemView: View):ViewHolder(itemView){
        val binding = BasketBinding.bind(itemView)
        @SuppressLint("SetTextI18n")
        fun bind(basket: Basket){
            basket.image?.let { binding.Image.setImageResource(it) }
            binding.name.text = basket.nameProduct
            binding.about.text = basket.aboutProduct
            binding.price.text = "${basket.price}sm"
            binding.plus.setOnClickListener {
                binding.counter.text = "${binding.counter.text.toString().toInt() + 1}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
       return BasketViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.basket,parent,false))
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BasketDiffUtil:DiffUtil.ItemCallback<Basket>(){
    override fun areItemsTheSame(oldItem: Basket, newItem: Basket): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Basket, newItem: Basket): Boolean {
return oldItem == newItem
    }

}