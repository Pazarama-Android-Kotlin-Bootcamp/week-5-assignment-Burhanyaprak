package com.burhanyaprak.patikaweekfive.data.repository

import com.burhanyaprak.patikaweekfive.data.local.database.entity.UserEntity
import com.burhanyaprak.patikaweekfive.data.model.Users
import retrofit2.Call

interface UserRepository {
    fun getUsers(): Call<List<Users>>
    fun getPostById(id: Int): UserEntity?
}