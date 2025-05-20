package com.repositories.searchRepository

import com.searchUseCases.entities.SearchEntity
import com.searchUseCases.repository.ISearchRepository
import com.githubRepositoriesService.githubRepositoriesDataSource.IRemoteGithubRepositoriesDataSource
import com.repositories.searchRepository.mappers.toSearchForTrendingGithubRepoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchRepositoryImpl(private val remoteGithubRepositoriesDataSource: IRemoteGithubRepositoriesDataSource) :
    ISearchRepository {

    override suspend fun searchForRepositories(query: String): Flow<List<SearchEntity>> =
        flow {
            val response = remoteGithubRepositoriesDataSource.searchForRepositories(query)

            response?.items?.filterNotNull()?.map { it.toSearchForTrendingGithubRepoEntity() }
                ?.let {
                    emit(it)
                } ?: throw Exception("Unknown Error")

        }.flowOn(Dispatchers.IO)
}