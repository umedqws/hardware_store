package com.example.hardwarestore.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hardwarestore.R
import com.example.hardwarestore.databinding.CommentBinding
import com.example.hardwarestore.model.Review

class ReviewAdapter:ListAdapter<Review, ReviewAdapter.ReviewViewHolder>(ReviewDiffUtils()) {

    inner class ReviewViewHolder(itemView: View):ViewHolder(itemView){
        val binding = CommentBinding.bind(itemView)
        fun bind(review: Review){
            binding.name.text = review.userName
            binding.comment.text = review.comment
            binding.ratingBar.rating = review.rating.toFloat()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.comment,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ReviewDiffUtils:DiffUtil.ItemCallback<Review>(){
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }

}