package com.edwinyosua.githubuserapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinyosua.githubuserapp.R
import com.edwinyosua.githubuserapp.data.response.Item
import com.edwinyosua.githubuserapp.databinding.FragmentHomeBinding
import com.edwinyosua.githubuserapp.ui.MainViewModel
import com.edwinyosua.githubuserapp.ui.adapter.UserRecycleAdptr


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var userAdptr: UserRecycleAdptr

//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        //to handle device back button
//        activity?.onBackPressedDispatcher?.addCallback(
//            requireActivity(),
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//                }
//            })
//    }


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


        setRecyclerView()


        //get userlistdata
        viewModel.userListData.observe(viewLifecycleOwner) { userList ->
            userAdptr.setList(userList)
        }
        userAdptr = UserRecycleAdptr()
        binding.rvReview.adapter = userAdptr


        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }


        setItemClick()

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


    private fun setItemClick() {
        userAdptr.setOnUserClick(object : UserRecycleAdptr.OnUserClick {
            override fun onUserClicked(item: Item) {
                val detailFrag = DetailUserFragment()
                val fragMgr = requireActivity().supportFragmentManager
                fragMgr.beginTransaction().apply {
                    replace(
                        R.id.fragContainer,
                        detailFrag
                    )
                    addToBackStack(null)
                    commit()
                }
            }
        })
    }


    private fun setRecyclerView() {
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
        Log.d("navigateHome","onSuccess")
    }

}

