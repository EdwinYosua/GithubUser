package com.edwinyosua.githubuserapp.ui.adapter

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.edwinyosua.githubuserapp.R
import com.edwinyosua.githubuserapp.ui.fragment.FollowerFragment
import com.edwinyosua.githubuserapp.ui.fragment.FollowingFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailPagerAdptr(private val context: Context, private val bundle: Bundle) :
    FragmentStateAdapter(context as AppCompatActivity),
    TabLayoutMediator.TabConfigurationStrategy {


    @StringRes
    private val TAB_TITLES = intArrayOf(
        R.string.follower,
        R.string.following
    )

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var frgmnt: Fragment? = null
        when (position) {
            0 -> frgmnt = FollowerFragment().apply { arguments = bundle }
            1 -> frgmnt = FollowingFragment().apply { arguments = bundle }
        }
        return frgmnt as Fragment
    }


    override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
        tab.text = context.resources.getString(TAB_TITLES[position])
    }

}