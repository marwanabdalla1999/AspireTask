package com.githubRepositoriesService.di

import com.githubRepositoriesService.apiService.GithubRepositoriesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DiApiServiceModule {

    @Provides
    @Singleton
    fun provideGithubRepositoriesApiService(retrofit: Retrofit): GithubRepositoriesApiService {
        return retrofit.create(GithubRepositoriesApiService::class.java)
    }
}