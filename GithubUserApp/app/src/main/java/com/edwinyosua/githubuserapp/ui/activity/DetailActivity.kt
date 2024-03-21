package com.edwinyosua.githubuserapp.ui.activity

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.edwinyosua.githubuserapp.R
import com.edwinyosua.githubuserapp.databinding.ActivityDetailBinding
import com.edwinyosua.githubuserapp.ui.activity.DetailActivity.Companion.TAB_TITLES
import com.edwinyosua.githubuserapp.ui.adapter.DetailPagerAdptr
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTabLayout()
    }

    private fun setTabLayout() {
        val viewPgr: ViewPager2 = binding.viewPager
        viewPgr.adapter = DetailPagerAdptr(this@DetailActivity, Bundle())

        TabLayoutMediator(binding.tabLayout, viewPgr) { tab, position ->
            tab.text = getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }
}