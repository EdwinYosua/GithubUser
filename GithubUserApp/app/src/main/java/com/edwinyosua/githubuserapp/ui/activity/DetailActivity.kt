package com.edwinyosua.githubuserapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.edwinyosua.githubuserapp.R
import com.edwinyosua.githubuserapp.data.database.FavoriteUser
import com.edwinyosua.githubuserapp.databinding.ActivityDetailBinding
import com.edwinyosua.githubuserapp.ui.adapter.DetailPagerAdptr
import com.edwinyosua.githubuserapp.ui.viewmodel.DetailViewModel
import com.edwinyosua.githubuserapp.ui.viewmodel.FavoriteUserViewModel
import com.edwinyosua.githubuserapp.ui.viewmodelfactory.FavViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_USERNAME = "extra_username"


        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.follower,
            R.string.following
        )
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewMdl: DetailViewModel
    private lateinit var favViewModel: FavoriteUserViewModel
    private lateinit var favUserName: String
    private lateinit var favAvatarUrl: String

    private var buttonState: Boolean = false
    private var favUser: FavoriteUser? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.prgBar.visibility = View.VISIBLE

        val userName = intent.getStringExtra(EXTRA_USERNAME)

        val bundle = Bundle().apply {
            putString(EXTRA_USERNAME, userName)
        }


        viewMdl = ViewModelProvider(this)[DetailViewModel::class.java]
        favViewModel = obtainViewModel(this@DetailActivity)


        //Set Tablayout Viewpager
        setTabLayout(bundle)

        //Set clicked User Data
        getUsersData(userName)
        viewMdl.users.observe(this) { user ->
            binding.apply {
                txvUserName.text = user.login
                txvName.text = user.name
                txvFollower.text = user.followers
                txvFollowing.text = user.following

                Glide.with(this@DetailActivity)
                    .load(user.avatarUrl)
                    .transform(CircleCrop())
                    .into(imgDetail)
            }
            binding.prgBar.visibility = View.GONE

            favUserName = user.login.toString()
            favAvatarUrl = user.avatarUrl.toString()
            favUser = FavoriteUser(userName = favUserName, avatarUrl = favAvatarUrl)
        }

        //set favorite function
        if (userName != null) {
            favViewModel.getFavUserByUsername(userName).observe(this) { favoriteUser ->
                if (favoriteUser != null) {
                    buttonState = true
                    binding.favButton.setImageResource(R.drawable.baseline_favorite_24)
                }
                binding.favButton.setOnClickListener {
                    if (!buttonState) {
                        //adding fav user
                        buttonState = true
                        binding.favButton.setImageResource(R.drawable.baseline_favorite_24)
                        favViewModel.insert(favUser as FavoriteUser)
                        showToast("User Ditambahkan ke Favorit")
                    } else {
                        //delete fav user
                        buttonState = false
                        binding.favButton.setImageResource(R.drawable.baseline_favorite_border_24)
                        favViewModel.delete(favoriteUser)
                        showToast("User Dihapus dari Favorit")
                    }
                }
            }
        }



        //Set menu topbar
        binding.topBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu1 -> {
                    startActivity(Intent(this@DetailActivity, FavoriteActivity::class.java))
                    true
                }

                R.id.menu2 -> {
                    startActivity(Intent(this@DetailActivity, SettingActivity::class.java))
                    true
                }

                else -> false
            }
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteUserViewModel {
        val factory = FavViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[FavoriteUserViewModel::class.java]
    }

    private fun getUsersData(username: String?) {
        username?.let {
            viewMdl.loadClickedUsers(it)
        }
    }

    private fun setTabLayout(bundle: Bundle) {
        val viewPgr: ViewPager2 = binding.viewPager
        viewPgr.adapter = DetailPagerAdptr(this, bundle)
        TabLayoutMediator(binding.tabLayout, viewPgr) { tab, position ->
            tab.text = getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }

}
