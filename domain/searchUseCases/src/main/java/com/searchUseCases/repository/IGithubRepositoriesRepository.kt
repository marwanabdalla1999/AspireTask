package com.searchUseCases.repository

import com.searchUseCases.entities.SearchEntity
import kotlinx.coroutines.flow.Flow

interface IGithubRepositoriesRepository {

   suspend fun searchForRepositories(query:String): Flow<List<SearchEntity>>
}