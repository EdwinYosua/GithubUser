package com.edwinyosua.githubuserapp.retrofit


import com.edwinyosua.githubuserapp.data.response.GitHubUserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getGithubData(
        @Query("q") query: String
    ): Call<GitHubUserResponse>
}