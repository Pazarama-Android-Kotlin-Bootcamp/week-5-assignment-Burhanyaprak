package com.burhanyaprak.patikaweekfive.data.repository

import com.burhanyaprak.patikaweekfive.data.local.database.entity.PostEntity
import com.burhanyaprak.patikaweekfive.data.model.Post
import retrofit2.Call

interface PostRepository {
    fun getPosts(): Call<List<Post>>
    fun getPostById(id: Int): PostEntity?
    fun insertFavoritePost(post: PostEntity)
    fun deleteFavoritePost(id: String)
    fun getFavorites(): List<PostEntity>
}