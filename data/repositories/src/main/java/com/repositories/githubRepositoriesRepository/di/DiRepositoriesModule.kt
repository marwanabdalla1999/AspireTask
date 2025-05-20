package com.repositories.githubRepositoriesRepository.di

import com.githubRepositoriesService.githubRepositoriesDataSource.IRemoteGithubRepositoriesDataSource
import com.searchUseCases.repository.IGithubRepositoriesRepository
import com.repositories.githubRepositoriesRepository.GithubRepositoriesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DiRepositoriesModule {

    @Singleton
    @Provides
    fun provideGithubRepositoriesRepository(remoteGithubRepositories: IRemoteGithubRepositoriesDataSource): IGithubRepositoriesRepository =
        GithubRepositoriesRepositoryImpl(remoteGithubRepositories)

}