package com.edwinyosua.githubuserapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinyosua.githubuserapp.data.response.Item
import com.edwinyosua.githubuserapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //to handle device back button
        activity?.onBackPressedDispatcher?.addCallback(
            requireActivity(),
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onPressedBackButtonHandle()
                }
            })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        setReyclerView()


        //get userlistdata
        viewModel.userListData.observe(viewLifecycleOwner) { userList ->
            setListUserAdpt(userList)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        //Searchbar Searchview
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    viewModel.loadUserListData(searchView.text)
                    false
                }
        }
    }

    private fun setListUserAdpt(userList: List<Item>) {
        val adpt = UserListAdptr()
        adpt.submitList(userList)
        binding.rvReview.adapter = adpt
    }

    private fun setReyclerView() {
        val layoutMgr = LinearLayoutManager(requireActivity())
        binding.rvReview.layoutManager = layoutMgr
        val itemDecor = DividerItemDecoration(requireActivity(), layoutMgr.orientation)
        binding.rvReview.addItemDecoration(itemDecor)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progbar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun onPressedBackButtonHandle() {
        with(binding) {
            if (!searchView.isShowing) {
                navigateHome()
            }
            searchView.hide()
        }
    }

    private fun navigateHome() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}

