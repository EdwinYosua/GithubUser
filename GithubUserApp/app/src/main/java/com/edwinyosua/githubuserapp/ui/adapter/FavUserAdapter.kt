package com.edwinyosua.githubuserapp.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edwinyosua.githubuserapp.data.database.FavoriteUser
import com.edwinyosua.githubuserapp.databinding.GithubItemBinding
import com.edwinyosua.githubuserapp.ui.activity.DetailActivity
import com.edwinyosua.githubuserapp.utils.FavUserDiffCallBack

class FavUserAdapter : RecyclerView.Adapter<FavUserAdapter.FavUserHolder>() {

    private val listFavUser = ArrayList<FavoriteUser>()
    fun setListFav(listFav: List<FavoriteUser>) {
        val diffCallBack = FavUserDiffCallBack(this.listFavUser, listFav)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        this.listFavUser.clear()
        this.listFavUser.addAll(listFav)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class FavUserHolder(private val binding: GithubItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favUser: FavoriteUser) {
            with(binding) {
                txvGithubitem.text = favUser.userName

                Glide.with(itemView.context)
                    .load(favUser.avatarUrl)
                    .into(binding.imgAvatar)


                cvItem.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_USERNAME, favUser.userName)
                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavUserAdapter.FavUserHolder {
        val binding = GithubItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavUserHolder(binding)
    }

    override fun onBindViewHolder(holder: FavUserAdapter.FavUserHolder, position: Int) {
        holder.bind(listFavUser[position])
    }

    override fun getItemCount(): Int = listFavUser.size
}