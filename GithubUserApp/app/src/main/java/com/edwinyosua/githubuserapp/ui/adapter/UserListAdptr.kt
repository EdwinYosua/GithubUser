package com.edwinyosua.githubuserapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edwinyosua.githubuserapp.data.response.GithubUserResponse
import com.edwinyosua.githubuserapp.data.response.Item
import com.edwinyosua.githubuserapp.databinding.GithubItemBinding


class UserListAdptr : ListAdapter<Item, UserListAdptr.UserHolder>(DIFF_CALLBACK) {

    private lateinit var onUserClick: OnUserClick

    class UserHolder(private val binding: GithubItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(githubData: Item) {
            binding.txvGithubitem.text = githubData.login

            Glide.with(itemView)
                .load(githubData.avatarUrl)
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
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnUserClick {
        fun onUserClicked(user: GithubUserResponse)
    }

    private fun setOnUserClick(onUserClick: OnUserClick) {
        this.onUserClick = onUserClick
    }


}