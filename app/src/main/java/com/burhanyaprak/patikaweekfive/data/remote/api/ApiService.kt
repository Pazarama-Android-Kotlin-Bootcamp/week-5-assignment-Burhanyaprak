package com.burhanyaprak.patikaweekfive.data.remote.api

import com.burhanyaprak.patikaweekfive.data.model.Post
import com.burhanyaprak.patikaweekfive.data.model.Users
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("users")
    fun getUsers(): Call<List<Users>>

    @DELETE("posts/{id}")
    fun deletePost(@Path("{id}") id: String): Call<Post>

}
