package com.burhanyaprak.patikaweekfive.data.di

import com.burhanyaprak.patikaweekfive.data.repository.PostRepositoryImpl
import com.burhanyaprak.patikaweekfive.data.repository.UserRepository
import com.burhanyaprak.patikaweekfive.data.repository.UserRepositoryImpl
import com.burhanyaprak.patikaweekfive.data.local.database.PostsDatabase
import com.burhanyaprak.patikaweekfive.data.remote.api.ApiService
import com.burhanyaprak.patikaweekfive.data.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class PostsModule {
    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun providePostRepository(apiService: ApiService, postsDatabase: PostsDatabase) : PostRepository {
        return PostRepositoryImpl(apiService, postsDatabase)
    }
}