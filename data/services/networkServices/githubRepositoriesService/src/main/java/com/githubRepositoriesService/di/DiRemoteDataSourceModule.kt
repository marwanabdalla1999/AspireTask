package com.githubRepositoriesService.di

import com.githubRepositoriesService.apiService.GithubRepositoriesApiService
import com.githubRepositoriesService.githubRepositoriesDataSource.IRemoteGithubRepositoriesDataSource
import com.githubRepositoriesService.githubRepositoriesDataSource.RemoteGithubRepositoriesDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DiRemoteDataSourceModule {

    @Provides
    fun provideRemoteGithubRepositories(githubRepositoriesApiService: GithubRepositoriesApiService): IRemoteGithubRepositoriesDataSource =
        RemoteGithubRepositoriesDataSourceImpl(githubRepositoriesApiService)

}