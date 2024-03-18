package com.edwinyosua.githubuserapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.edwinyosua.githubuserapp.R

class DetailUserFragment : Fragment() {


    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.follower,
            R.string.following
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_user, container, false)
    }


}