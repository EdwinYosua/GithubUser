package com.edwinyosua.githubuserapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.edwinyosua.githubuserapp.R
import com.edwinyosua.githubuserapp.databinding.ActivityMainBinding
import com.edwinyosua.githubuserapp.ui.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragContainer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadFrag(HomeFragment())
    }

    override fun onResume() {
        super.onResume()
        loadFrag(HomeFragment())
    }

    private fun loadFrag(frg: Fragment) {
        val fragMgr = supportFragmentManager
        fragMgr.beginTransaction().apply {
            replace(R.id.fragContainer, frg)
            addToBackStack(null)
            commit()
        }
    }
}