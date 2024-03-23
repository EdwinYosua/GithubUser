package com.edwinyosua.githubuserapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edwinyosua.githubuserapp.R
import com.edwinyosua.githubuserapp.databinding.FragmentFollowerBinding
import com.edwinyosua.githubuserapp.ui.adapter.UserRecycleAdptr
import com.edwinyosua.githubuserapp.ui.viewmodel.DetailViewModel


class FollowerFragment : Fragment() {

    private var _binding : FragmentFollowerBinding? = null
    private val binding get() =_binding!!
    private lateinit var viewMdl : DetailViewModel
    private lateinit var adptr : UserRecycleAdptr
    private lateinit var username : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

}