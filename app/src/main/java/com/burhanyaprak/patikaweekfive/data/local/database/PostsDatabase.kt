package com.burhanyaprak.patikaweekfive.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.burhanyaprak.patikaweekfive.data.local.database.converter.DaoConverter
import com.burhanyaprak.patikaweekfive.data.local.database.dao.PostDao
import com.burhanyaprak.patikaweekfive.data.local.database.dao.UserDao
import com.burhanyaprak.patikaweekfive.data.local.database.entity.PostEntity
import com.burhanyaprak.patikaweekfive.data.local.database.entity.UserEntity
import com.burhanyaprak.patikaweekfive.utils.Constants

@Database(entities = [PostEntity::class, UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(DaoConverter::class)
abstract class PostsDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun userDao(): UserDao

    companion object {
        private var instance: PostsDatabase? = null

        fun getDatabase(context: Context): PostsDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, PostsDatabase::class.java, Constants.TABLE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

    }
}