package com.edwinyosua.githubuserapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.edwinyosua.githubuserapp.R
import com.edwinyosua.githubuserapp.databinding.FragmentDetailUserBinding
import com.edwinyosua.githubuserapp.ui.adapter.DetailPagerAdptr
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserFragment : Fragment() {

    private var _binding: FragmentDetailUserBinding? = null
    private val binding get() = _binding!!

//    companion object {
//
//        const val EXTRA_USERNAME = "extra_username"
//
//
//        @StringRes
//        private val TAB_TITLES = intArrayOf(
//            R.string.follower,
//            R.string.following
//        )
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailUserBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setTabLayout()
//    }

//    private fun setTabLayout() {
//
//        val detailPagerAdptr = DetailPagerAdptr(requireContext(), Bundle())
//        val viewPager: ViewPager2 = binding.viewPager
//        viewPager.adapter = detailPagerAdptr
//
//        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
//            tab.text = getString(TAB_TITLES[position])
//        }.attach()
//    }
}