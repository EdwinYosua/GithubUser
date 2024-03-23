package com.edwinyosua.githubuserapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edwinyosua.githubuserapp.data.response.DetailUsersResponse
import com.edwinyosua.githubuserapp.databinding.GithubItemBinding


class ListDetailAdapter :
    ListAdapter<DetailUsersResponse, ListDetailAdapter.ListHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DetailUsersResponse>() {
            override fun areItemsTheSame(
                oldItem: DetailUsersResponse,
                newItem: DetailUsersResponse
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DetailUsersResponse,
                newItem: DetailUsersResponse
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    class ListHolder(private val binding: GithubItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(list: DetailUsersResponse) {
            binding.txvGithubitem.text = "${list.login}"

            Glide.with(itemView.context)
                .load(list.avatarUrl)
                .into(binding.imgAvatar)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListHolder {
        val binding =
            GithubItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListHolder(binding)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val list = getItem(position)
        holder.bind(list)
    }

}