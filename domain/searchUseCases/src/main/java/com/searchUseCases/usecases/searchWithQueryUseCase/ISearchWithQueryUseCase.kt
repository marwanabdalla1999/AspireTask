package com.searchUseCases.usecases.searchWithQueryUseCase

import com.searchUseCases.entities.SearchEntity
import kotlinx.coroutines.flow.Flow

interface ISearchWithQueryUseCase {

   suspend operator fun invoke(query: String): Flow<List<SearchEntity>>
}