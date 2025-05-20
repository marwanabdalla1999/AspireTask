package com.searchInGithubRepositoriesFeature.di

import com.searchUseCases.repository.ISearchRepository
import com.searchUseCases.usecases.searchWithQueryUseCase.ISearchWithQueryUseCase
import com.searchUseCases.usecases.searchWithQueryUseCase.SearchWithQueryUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DiUseCasesModule {

    @Singleton
    @Provides
    fun provideSearchWithQueryUseCase(githubRepositoriesRepository: ISearchRepository): ISearchWithQueryUseCase =
        SearchWithQueryUseCaseImpl(githubRepositoriesRepository)


}