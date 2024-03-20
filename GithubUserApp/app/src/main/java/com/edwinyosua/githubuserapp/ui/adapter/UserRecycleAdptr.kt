package com.edwinyosua.githubuserapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.edwinyosua.githubuserapp.R
import com.edwinyosua.githubuserapp.data.response.Item
import com.edwinyosua.githubuserapp.utils.DiffCallBackUtil

class UserRecycleAdptr : RecyclerView.Adapter<UserRecycleAdptr.UserListHolder>() {

    private var userList = ArrayList<Item>()


    fun setList(list: List<Item>) {
        val diffCallBack = DiffCallBackUtil(userList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)

        userList.clear()
        userList.addAll(list)
        diffResult.dispatchUpdatesTo(this)

    }


    class UserListHolder(v: View) : RecyclerView.ViewHolder(v) {
        val userName: TextView = v.findViewById(R.id.txv_githubitem)
        val userImg: ImageView = v.findViewById(R.id.img_avatar)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): UserListHolder {
        return UserListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.github_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserListHolder, position: Int) {
        val currentUser = userList[position]
        holder.userName.text = currentUser.login

        Glide.with(holder.itemView)
            .load(currentUser.avatarUrl)
            .transform(CircleCrop())
            .into(holder.userImg)

    }

    override fun getItemCount(): Int = userList.size
}