package com.edwinyosua.githubuserapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinyosua.githubuserapp.data.response.Item
import com.edwinyosua.githubuserapp.databinding.ActivityMainBinding
import com.edwinyosua.githubuserapp.ui.adapter.UserRecycleAdptr
import com.edwinyosua.githubuserapp.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdptr: UserRecycleAdptr
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewModel.isLoading.observe(this) {
            showLoading(it)
        }


        //set recyclerview
        setRecycleV()
        viewModel.userListData.observe(this) { userList ->
            userAdptr.setList(userList)
        }
        userAdptr = UserRecycleAdptr()
        binding.rvList.adapter = userAdptr


        //Set clickable item
        setItemClick()

        //Set Searchview
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
                startActivity(
                    Intent(this@MainActivity, DetailActivity::class.java)
                        .putExtra(DetailActivity.EXTRA_USERNAME, item.login)
                )

            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setRecycleV() {
        val layoutMgr = LinearLayoutManager(this)
        binding.rvList.layoutManager = layoutMgr
        val itemDcor = DividerItemDecoration(this, layoutMgr.orientation)
        binding.rvList.addItemDecoration(itemDcor)
    }

}