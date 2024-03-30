package com.edwinyosua.githubuserapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinyosua.githubuserapp.R
import com.edwinyosua.githubuserapp.data.response.Item
import com.edwinyosua.githubuserapp.databinding.ActivityMainBinding
import com.edwinyosua.githubuserapp.ui.adapter.UserRecycleAdptr
import com.edwinyosua.githubuserapp.ui.viewmodel.MainViewModel
import com.edwinyosua.githubuserapp.ui.viewmodel.SettingViewModel
import com.edwinyosua.githubuserapp.data.datastore.SettingPreferences
import com.edwinyosua.githubuserapp.data.datastore.SettingViewModelFactory
import com.edwinyosua.githubuserapp.data.datastore.dataStore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdptr: UserRecycleAdptr
    private lateinit var viewModel: MainViewModel
    private lateinit var settingViewModel : SettingViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(application.dataStore)
        settingViewModel = ViewModelProvider(this, SettingViewModelFactory(pref))[SettingViewModel::class.java]

        settingViewModel.getThemeSettings().observe(this) { isDarkMode: Boolean ->
            if(isDarkMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //set loading
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
        setSearchView()

        //Set menu topbar
        binding.topBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu1 -> {
                    startActivity(Intent(this@MainActivity, FavoriteActivity::class.java))
                    true
                }

                R.id.menu2 -> {
                    startActivity(Intent(this@MainActivity, SettingActivity::class.java))
                    true
                }
                else -> false
            }
        }


    }


    private fun setSearchView() {
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