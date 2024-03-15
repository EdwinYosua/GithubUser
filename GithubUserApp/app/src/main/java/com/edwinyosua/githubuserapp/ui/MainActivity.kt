package com.edwinyosua.githubuserapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.edwinyosua.githubuserapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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