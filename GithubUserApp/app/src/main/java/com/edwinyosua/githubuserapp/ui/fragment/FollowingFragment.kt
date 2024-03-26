package com.edwinyosua.githubuserapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinyosua.githubuserapp.databinding.FragmentFollowingBinding
import com.edwinyosua.githubuserapp.ui.activity.DetailActivity
import com.edwinyosua.githubuserapp.ui.adapter.ListDetailAdapter
import com.edwinyosua.githubuserapp.ui.viewmodel.DetailViewModel

class FollowingFragment : Fragment() {


    private var _binding: FragmentFollowingBinding? = null
    private val binding = _binding!!
    private lateinit var viewMdl: DetailViewModel
    private lateinit var adptr: ListDetailAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progresBar.visibility = View.VISIBLE
        viewMdl = ViewModelProvider(requireActivity())[DetailViewModel::class.java]

        viewMdl.getFollowingList(arguments?.getString(DetailActivity.EXTRA_USERNAME).toString())

        viewMdl.followingList.observe(viewLifecycleOwner) { list ->
            binding.rvFollowing.apply {
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
            adptr.submitList(list)
            binding.progresBar.visibility = View.GONE
        }
        adptr = ListDetailAdapter()
        binding.rvFollowing.adapter = adptr
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}