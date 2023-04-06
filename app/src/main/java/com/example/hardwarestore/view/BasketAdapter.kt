package com.example.hardwarestore.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hardwarestore.R
import com.example.hardwarestore.databinding.BasketBinding
import com.example.hardwarestore.model.History
import com.example.hardwarestore.model.Products

class BasketAdapter : ListAdapter<Products, BasketAdapter.BasketViewHolder>(BasketDiffUtil()) {

    var onClick: ((Int) -> (Unit))? = null
    var onDelete: ((Int)->Unit)? = null


    inner class BasketViewHolder(itemView: View) : ViewHolder(itemView) {
        val binding = BasketBinding.bind(itemView)
       init {

       }

        @SuppressLint("SetTextI18n")
        fun bind(products: Products) {
            products.image?.let { binding.Image.setImageResource(it) }
            binding.name.text = products.nameProduct
            binding.about.text = products.aboutProduct
            binding.price.text = "${products.price} sm"

            binding.plus.setOnClickListener {
                binding.counter.text = "${binding.counter.text.toString().toInt() + 1}"
                binding.price.text =
                    "${products.price!! * binding.counter.text.toString().toInt()}sm"
                onClick?.invoke(products.price.toInt())
            }
            binding.minus.setOnClickListener {
                if (binding.counter.text.toString().toInt() != 0) {
                    onClick?.invoke(-products.price!!.toInt())
                    binding.counter.text = "${binding.counter.text.toString().toInt() - 1}"
                    binding.price.text =
                        "${products.price!! * binding.counter.text.toString().toInt()}sm"
                }
            }
            binding.delete.setOnClickListener {
                onDelete?.invoke(products.id)
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


class BasketDiffUtil:DiffUtil.ItemCallback<Products>(){

    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem == newItem
    }

}