package com.burhanyaprak.patikaweekfive.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.burhanyaprak.patikaweekfive.data.local.database.base.BaseDao
import com.burhanyaprak.patikaweekfive.data.local.database.entity.PostEntity
import com.burhanyaprak.patikaweekfive.utils.Constants

/**
 * Created by merttoptas on 16.10.2022.
 */

@Dao
interface PostDao : BaseDao<PostEntity> {
    @Query("SELECT * FROM ${Constants.TABLE_POST_NAME}")
    fun getAllPosts(): List<PostEntity>

    @Query("DELETE FROM ${Constants.TABLE_POST_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${Constants.TABLE_POST_NAME} WHERE postId = :postId")
    fun getPostById(postId: String): PostEntity?

    @Query("DELETE FROM ${Constants.TABLE_POST_NAME} WHERE postId = :postId")
    fun deleteFavoriteById(postId: String)
}