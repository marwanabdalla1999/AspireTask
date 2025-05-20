package com.repositories.searchRepository.di

import com.githubRepositoriesService.githubRepositoriesDataSource.IRemoteGithubRepositoriesDataSource
import com.searchUseCases.repository.ISearchRepository
import com.repositories.searchRepository.SearchRepositoryImpl
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
    fun provideSearchRepository(remoteGithubRepositories: IRemoteGithubRepositoriesDataSource): ISearchRepository =
        SearchRepositoryImpl(remoteGithubRepositories)

}