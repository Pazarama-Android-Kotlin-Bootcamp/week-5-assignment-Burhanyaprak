package com.burhanyaprak.patikaweekfive.ui.posts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.burhanyaprak.patikaweekfive.data.model.PostDTO
import com.burhanyaprak.patikaweekfive.databinding.ItemPostLayoutBinding

class PostsAdapter(
    private val listener: OnPostClickListener
) : ListAdapter<PostDTO, PostsAdapter.PostViewHolder>(PostsDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val postsBinding = ItemPostLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostViewHolder(postsBinding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class PostViewHolder(private val binding: ItemPostLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostDTO, listener: OnPostClickListener) {
            binding.dataHolder = post
            binding.imageViewPostImage.setOnClickListener {
                listener.onPostClick(post)
            }
            binding.executePendingBindings()
        }
    }

    class PostsDiffUtil : DiffUtil.ItemCallback<PostDTO>() {
        override fun areItemsTheSame(oldItem: PostDTO, newItem: PostDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostDTO, newItem: PostDTO): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnPostClickListener {
    fun onPostClick(post: PostDTO)
}