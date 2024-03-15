package com.edwinyosua.githubuserapp.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.edwinyosua.githubuserapp.R
import com.edwinyosua.githubuserapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val fragMgr = supportFragmentManager
        val homeFrag = HomeFragment()
        val frag = fragMgr.findFragmentByTag(HomeFragment::class.java.simpleName)
        if(frag !is HomeFragment) {
            Log.d("HomeFragment", "Fragment Name : " + HomeFragment::class.java.simpleName)
            fragMgr
                .beginTransaction()
                .add(R.id.fragContainer, homeFrag, HomeFragment::class.java.simpleName)
                .commit()
        }
    }
}