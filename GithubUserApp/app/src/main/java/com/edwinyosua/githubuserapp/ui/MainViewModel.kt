package com.edwinyosua.githubuserapp.ui

import android.text.Editable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edwinyosua.githubuserapp.data.response.GithubUserResponse
import com.edwinyosua.githubuserapp.data.response.Item
import com.edwinyosua.githubuserapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {


    private val _userListData = MutableLiveData<List<Item>>()
    val userListData: LiveData<List<Item>> = _userListData


    fun loadUserListData(query: Editable) {
        ApiConfig.getApiService()
            .searchUsersList(query)
            .enqueue(object : Callback<GithubUserResponse> {
                override fun onResponse(
                    call: Call<GithubUserResponse>,
                    response: Response<GithubUserResponse>
                ) {
                    if (response.isSuccessful) {
                        _userListData.value = response.body()?.items
                    } else {
                        Log.e("loadUserList", "onFailure : ${response.message()}")
                    }
                }
                override fun onFailure(call: Call<GithubUserResponse>, t: Throwable) {
                    Log.e("loadUserList", "onFailure : ${t.message}")
                }
            })
    }

    fun loadAllUserListData() {
        ApiConfig.getApiService().getAllUserList(perPage = 30, sinceUserId = 0)
            .enqueue(object : Callback<List<Item>> {
                override fun onResponse(
                    call: Call<List<Item>>,
                    response: Response<List<Item>>
                ) {
                    if (response.isSuccessful) {
                        _userListData.value = response.body()
                    } else {
                        Log.e("loadAllUser", "onFailure : ${response.message()}")
                        _userListData.value = emptyList()
                    }
                }
                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                    Log.e("loadAllUser", "onFailure: ${t.message}")
                    _userListData.value = emptyList()

                }
            })
    }
}