package com.burhanyaprak.patikaweekfive.data.di

import android.content.Context
import com.burhanyaprak.patikaweekfive.data.local.database.PostsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    @Singleton
    fun providePostsDatabase(@ApplicationContext appContext: Context): PostsDatabase {
        return PostsDatabase.getDatabase(appContext)
    }

    @Singleton
    @Provides
    fun providePostDao(db: PostsDatabase) = db.postDao()

    @Singleton
    @Provides
    fun provideUserDao(db: PostsDatabase) = db.userDao()
}