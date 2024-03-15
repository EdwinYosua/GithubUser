package com.edwinyosua.githubuserapp.retrofit


import com.edwinyosua.githubuserapp.data.response.GitHubUserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("search/users")
    fun getGithubData(
        @Query("q") query: String
    ): Call<GitHubUserResponse>
}