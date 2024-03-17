package com.edwinyosua.githubuserapp.retrofit


import android.text.Editable
import com.edwinyosua.githubuserapp.data.response.GitHubUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getGithubData(
        @Query("q") query: Editable
    ): Call<GitHubUserResponse>
}