package com.edwinyosua.githubuserapp.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailPagerAdptr(private val context: Context) : FragmentStateAdapter(context as FragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var frgmnt: Fragment? = null
        when (position) {
            0 -> frgmnt = FollowerFragment()
            1 -> frgmnt = FollowingFragment()
        }
        return frgmnt as Fragment
    }

}