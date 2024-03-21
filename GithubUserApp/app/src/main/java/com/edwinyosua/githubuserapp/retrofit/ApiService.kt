package com.edwinyosua.githubuserapp.retrofit


import android.text.Editable
import com.edwinyosua.githubuserapp.data.response.DetailUsersResponse
import com.edwinyosua.githubuserapp.data.response.GithubUserResponse
import com.edwinyosua.githubuserapp.data.response.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun searchUsersList(
        @Query("q") query: Editable
    ): Call<GithubUserResponse>

    @GET("users")
    @Headers("Authorization: token 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")
    fun getAllUserList(
        @Query("per_page") perPage: Int,
        @Query("since") sinceUserId: Int,
    ): Call<List<Item>>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getClickedUser(
        @Path("username") username: String
    ): Call<DetailUsersResponse>
}