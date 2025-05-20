package com.searchUseCases.usecases.searchWithQueryUseCase

import com.searchUseCases.entities.SearchEntity
import com.searchUseCases.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow

class SearchWithQueryUseCaseImpl(private val githubRepositoriesRepository: ISearchRepository):
    ISearchWithQueryUseCase {

    override suspend operator fun invoke(query: String): Flow<List<SearchEntity>> =  githubRepositoriesRepository.searchForRepositories(query)
}