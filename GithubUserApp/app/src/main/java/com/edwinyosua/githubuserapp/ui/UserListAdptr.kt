package com.edwinyosua.githubuserapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edwinyosua.githubuserapp.data.response.ItemsItem
import com.edwinyosua.githubuserapp.databinding.GithubItemBinding


class UserListAdptr : ListAdapter<ItemsItem, UserListAdptr.UserHolder>(DIFF_CALLBACK) {
    class UserHolder(private val binding: GithubItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(githubData: ItemsItem) {
            binding.txvGithubitem.text = "${githubData.login}"

            Glide.with(itemView)
                .load("${githubData.avatarUrl}")
                .centerCrop()
                .into(binding.imgAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding = GithubItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHolder(binding)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val githubData = getItem(position)
        holder.bind(githubData)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

        }
    }


}