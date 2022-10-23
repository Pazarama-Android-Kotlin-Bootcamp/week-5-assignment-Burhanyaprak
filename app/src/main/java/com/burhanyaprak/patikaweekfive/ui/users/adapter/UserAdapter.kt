package com.burhanyaprak.patikaweekfive.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.burhanyaprak.patikaweekfive.data.model.UserDTO
import com.burhanyaprak.patikaweekfive.databinding.UserItemLayoutBinding

class UserAdapter :
    ListAdapter<UserDTO, UserAdapter.UserViewHolder>(
        UserAdapter.UsersDiffUtil()
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        return UserAdapter.UserViewHolder(
            UserItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class UserViewHolder(private val binding: UserItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserDTO) {
            binding.dataHolder = user
//            binding.ivPostImage.setOnClickListener {
//               // listener.onPostClick(user)
//            }
            binding.executePendingBindings()
        }
    }
    class UsersDiffUtil : DiffUtil.ItemCallback<UserDTO>() {
        override fun areItemsTheSame(oldItem: UserDTO, newItem: UserDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserDTO, newItem: UserDTO): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnUserClickListener {
    fun onPostClick(post: UserDTO)
}