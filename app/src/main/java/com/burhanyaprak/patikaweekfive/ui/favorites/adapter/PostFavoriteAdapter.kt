package com.burhanyaprak.patikaweekfive.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.burhanyaprak.patikaweekfive.data.model.PostDTO
import com.burhanyaprak.patikaweekfive.databinding.PostFavoritesLayoutBinding

class PostFavoriteAdapter : ListAdapter<PostDTO, PostFavoriteAdapter.PostFavoriteViewHolder>(
    PostsDiffUtil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostFavoriteViewHolder {
        val postsBinding = PostFavoritesLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostFavoriteViewHolder(postsBinding)
    }

    override fun onBindViewHolder(holder: PostFavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PostFavoriteViewHolder(private val binding: PostFavoritesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostDTO) {
            binding.dataHolder = post
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