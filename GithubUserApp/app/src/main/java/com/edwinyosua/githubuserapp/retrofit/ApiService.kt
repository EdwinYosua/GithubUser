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

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<DetailUsersResponse>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<DetailUsersResponse>>

}