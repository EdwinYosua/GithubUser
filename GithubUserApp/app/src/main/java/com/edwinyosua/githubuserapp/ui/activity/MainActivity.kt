package com.edwinyosua.githubuserapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.edwinyosua.githubuserapp.R
import com.edwinyosua.githubuserapp.databinding.ActivityMainBinding
import com.edwinyosua.githubuserapp.ui.MainViewModel
import com.edwinyosua.githubuserapp.ui.adapter.UserRecycleAdptr
import com.edwinyosua.githubuserapp.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdptr : UserRecycleAdptr
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setRecycleV()
        viewModel.userListData.observe(this){ userList ->
            userAdptr.setList(userList)
        }
        userAdptr = UserRecycleAdptr()
        binding.rvList.adapter = userAdptr
    }

    private fun setRecycleV() {
        val layoutMgr = LinearLayoutManager(this)
        binding.rvList.layoutManager = layoutMgr
        val itemDcor = DividerItemDecoration(this, layoutMgr.orientation)
        binding.rvList.addItemDecoration(itemDcor)
    }
}