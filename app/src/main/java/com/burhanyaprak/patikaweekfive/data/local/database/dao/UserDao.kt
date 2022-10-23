package com.burhanyaprak.patikaweekfive.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.burhanyaprak.patikaweekfive.data.local.database.base.BaseDao
import com.burhanyaprak.patikaweekfive.data.local.database.entity.UserEntity
import com.burhanyaprak.patikaweekfive.utils.Constants

@Dao
interface UserDao: BaseDao<UserEntity> {
    @Query("SELECT * FROM ${Constants.TABLE_USER_NAME}")
    fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM ${Constants.TABLE_USER_NAME} WHERE name = :id")
    fun getUserById(id: String): UserEntity?
}