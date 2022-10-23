package com.burhanyaprak.patikaweekfive.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.burhanyaprak.patikaweekfive.utils.Constants


@Entity(tableName = Constants.TABLE_USER_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "name") val userName: String?,
    @ColumnInfo(name = "username") val userUserName: String?,
    @ColumnInfo(name = "website") val userWebsite: String?,
)