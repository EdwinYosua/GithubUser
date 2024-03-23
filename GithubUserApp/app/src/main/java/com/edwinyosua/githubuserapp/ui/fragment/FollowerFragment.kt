package com.edwinyosua.githubuserapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinyosua.githubuserapp.R
import com.edwinyosua.githubuserapp.databinding.FragmentFollowerBinding
import com.edwinyosua.githubuserapp.ui.activity.DetailActivity
import com.edwinyosua.githubuserapp.ui.adapter.ListDetailAdapter
import com.edwinyosua.githubuserapp.ui.adapter.UserRecycleAdptr
import com.edwinyosua.githubuserapp.ui.viewmodel.DetailViewModel


class FollowerFragment : Fragment() {

    private var _binding : FragmentFollowerBinding? = null
    private val binding get() =_binding!!
    private lateinit var viewMdl : DetailViewModel
    private lateinit var adptr : ListDetailAdapter
    private lateinit var username : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewMdl = ViewModelProvider(requireActivity())[DetailViewModel::class.java]
        username = arguments?.getString(DetailActivity.EXTRA_USERNAME).toString()



        viewMdl.getFollowerList(username)
//        binding.tesText.text = username

        viewMdl.followerList.observe(viewLifecycleOwner) { list->
            adptr.submitList(list)
        }


        binding.rvFollower.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
        }

        adptr = ListDetailAdapter()
        binding.rvFollower.adapter = adptr
    }

}