package com.edwinyosua.githubuserapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edwinyosua.githubuserapp.data.response.DetailUsersResponse
import com.edwinyosua.githubuserapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val _users = MutableLiveData<DetailUsersResponse>()
    val users: LiveData<DetailUsersResponse> = _users

    fun loadClickedUsers(username: String) {
        val client = ApiConfig.getApiService().getClickedUser(username)
        client.enqueue(object : Callback<DetailUsersResponse> {
            override fun onResponse(
                call: Call<DetailUsersResponse>,
                response: Response<DetailUsersResponse>
            ) {
                if (response.isSuccessful) {
                    _users.value = response.body()
                    Log.d("loadClickedUsers", "Success")
                } else {
                    Log.e("LoadClickedUsers", "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUsersResponse>, t: Throwable) {
                Log.e("LoadClickedUsers", "onFailure : ${t.message}")
            }
        })
    }
}