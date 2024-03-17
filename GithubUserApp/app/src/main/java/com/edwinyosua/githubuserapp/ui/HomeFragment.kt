package com.edwinyosua.githubuserapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinyosua.githubuserapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
////to handle device back button
//        activity?.onBackPressedDispatcher?.addCallback(requireActivity(), object : OnBackPressedCallback(true){
//            override fun handleOnBackPressed() {
//                val homeFrag = HomeFragment()
//                val fragMgr = parentFragmentManager
//                fragMgr.beginTransaction().apply {
//                    replace(R.id.fragContainer, homeFrag, HomeFragment::class.java.simpleName)
//                    addToBackStack(null)
//                    commit()
//                }
//            }
//        })
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
        val layoutMgr = LinearLayoutManager(requireActivity())
        val itemDecor = DividerItemDecoration(requireActivity(), layoutMgr.orientation)



        binding.apply {
            rvReview.layoutManager = layoutMgr
            rvReview.addItemDecoration(itemDecor)
        }


//        //Set data in recyclerview when apps open
//        viewModel.setUserSearchData(
//            "a",
//            binding.rvReview,
//            binding.progbar
//        )


        //Searchbar Searchview
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    viewModel.setUserSearchData(searchView.text, binding.rvReview, binding.progbar)
//                    Toast.makeText(requireActivity(), searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }
    }


}