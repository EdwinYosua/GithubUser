package com.edwinyosua.githubuserapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinyosua.githubuserapp.databinding.ActivityFavoriteBinding
import com.edwinyosua.githubuserapp.ui.adapter.FavUserAdapter
import com.edwinyosua.githubuserapp.ui.viewmodel.FavoriteUserViewModel
import com.edwinyosua.githubuserapp.ui.viewmodelfactory.FavViewModelFactory

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewMdl: FavoriteUserViewModel
    private lateinit var adapter: FavUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewMdl = obtainViewModel(this@FavoriteActivity)

        viewMdl.getFavUserList().observe(this) { favUserList ->
            adapter.setListFav(favUserList)
        }

        adapter = FavUserAdapter()
        binding.rvFav.layoutManager = LinearLayoutManager(this)
        binding.rvFav.adapter = adapter

    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteUserViewModel {
        val factory = FavViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteUserViewModel::class.java]
    }
}