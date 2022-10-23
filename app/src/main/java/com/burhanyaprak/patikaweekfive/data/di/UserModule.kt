package com.burhanyaprak.patikaweekfive.data.di

import com.burhanyaprak.patikaweekfive.data.local.database.PostsDatabase
import com.burhanyaprak.patikaweekfive.data.remote.api.ApiService
import com.burhanyaprak.patikaweekfive.data.repository.UserRepository
import com.burhanyaprak.patikaweekfive.data.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UserModule {
    @Provides
    fun provideUserRepository(apiService: ApiService, postsDatabase: PostsDatabase) : UserRepository {
        return UserRepositoryImpl(apiService, postsDatabase)
    }
}