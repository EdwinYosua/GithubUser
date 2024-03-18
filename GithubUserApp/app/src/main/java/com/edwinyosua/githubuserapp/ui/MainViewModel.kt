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

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    init {
        loadAllUserListData()
    }

    fun loadUserListData(query: Editable) {
        _isLoading.value = true
        ApiConfig.getApiService()
            .searchUsersList(query)
            .enqueue(object : Callback<GithubUserResponse> {
                override fun onResponse(
                    call: Call<GithubUserResponse>,
                    response: Response<GithubUserResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _userListData.value = response.body()?.items

                    } else {
                        Log.e("loadUserList", "onFailure : ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<GithubUserResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.e("loadUserList", "onFailure : ${t.message}")
                }
            })
    }

    fun loadAllUserListData() {
        _isLoading.value = true
        ApiConfig.getApiService().getAllUserList(perPage = 30, sinceUserId = 0)
            .enqueue(object : Callback<List<Item>> {
                override fun onResponse(
                    call: Call<List<Item>>,
                    response: Response<List<Item>>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _userListData.postValue(response.body())
                        Log.d("loadAll","onSuccess : Load All Data User")
                    } else {
                        Log.e("loadAllUser", "onFailure : ${response.message()}")
//                        _userListData.value = emptyList()
                        _userListData.postValue(emptyList())

                    }
                }

                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                    _isLoading.value = false
                    Log.e("loadAllUser", "onFailure: ${t.message}")
//                    _userListData.value = emptyList()
                    _userListData.postValue(emptyList())
                }
            })
    }

}