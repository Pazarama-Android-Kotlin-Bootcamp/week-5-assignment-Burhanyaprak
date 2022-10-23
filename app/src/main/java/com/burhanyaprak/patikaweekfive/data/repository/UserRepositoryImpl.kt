package com.burhanyaprak.patikaweekfive.data.repository

import com.burhanyaprak.patikaweekfive.data.local.database.PostsDatabase
import com.burhanyaprak.patikaweekfive.data.local.database.entity.UserEntity
import com.burhanyaprak.patikaweekfive.data.model.Users
import com.burhanyaprak.patikaweekfive.data.remote.api.ApiService
import retrofit2.Call

class UserRepositoryImpl constructor(private val  apiService: ApiService, private val postsDatabase: PostsDatabase) :
    UserRepository {
    override fun getUsers(): Call<List<Users>> {
        return apiService.getUsers()
    }

    override fun getPostById(id: Int): UserEntity? {
        return postsDatabase.userDao().getUserById(id.toString())
    }
}