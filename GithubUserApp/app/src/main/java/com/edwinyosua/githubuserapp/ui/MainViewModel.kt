package com.edwinyosua.githubuserapp.ui

import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.edwinyosua.githubuserapp.data.response.GitHubUserResponse
import com.edwinyosua.githubuserapp.data.response.ItemsItem
import com.edwinyosua.githubuserapp.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {


    companion object {
        const val TAG = "MainViewModel"
    }


    //    fun setUserSearchData(rv: RecyclerView, pgBar: ProgressBar) {
    fun setUserSearchData(query: Editable, rv: RecyclerView, pgBar: ProgressBar) {
        showLoading(true, pgBar)
        ApiConfig.getApiService()
            .getGithubData(query)
            .enqueue(object : Callback<GitHubUserResponse> {
                override fun onResponse(
                    call: Call<GitHubUserResponse>,
                    response: Response<GitHubUserResponse>
                ) {
                    showLoading(false, pgBar)
                    if (response.isSuccessful) {
                        val responseBdy = response.body()
                        if (responseBdy != null) {
                            setDataUser(responseBdy.items, rv)
                        } else {
                            Log.e(TAG, "onFailure : ${response.message()}")
                        }
                    }
                }

                override fun onFailure(call: Call<GitHubUserResponse>, t: Throwable) {
                    showLoading(false, pgBar)
                    Log.e(TAG, "onFailure : ${t.message}")
                }
            })
    }

    fun setDataUser(userData: List<ItemsItem?>?, rv: RecyclerView) {
        val adpt = UserListAdptr()
        adpt.submitList(userData)
        rv.adapter = adpt
    }

    fun showLoading(isLoading: Boolean, progbar: ProgressBar) {
        if (isLoading) {
            progbar.visibility = View.VISIBLE
        } else {
            progbar.visibility = View.GONE
        }
    }
}