package com.searchUseCases.usecases

import com.searchUseCases.entities.SearchEntity
import com.searchUseCases.repository.IGithubRepositoriesRepository
import com.searchUseCases.usecases.searchWithQueryUseCase.SearchWithQueryUseCaseImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class SearchWithQueryUseCaseImplTest {

    private lateinit var repository: FakeSearchRepository
    private lateinit var useCase: SearchWithQueryUseCaseImpl

    @Before
    fun setup() {
        repository = FakeSearchRepository()
        useCase = SearchWithQueryUseCaseImpl(repository)
    }

    @Test
    fun `invoke should return repository results`() = runTest {
        val query = "compose"
        val expected = listOf(
            SearchEntity(repositoryName = "Repo1", description = "Desc1", repositoryOwnerName = "android1", repositoryOwnerAvatarUrl = "", starsCount = 100, language = "Kotlin"),
            SearchEntity(repositoryName = "Repo2", description = "Desc2", repositoryOwnerName = "android2", repositoryOwnerAvatarUrl = "", starsCount = 200, language = "Java")
        )
        repository.setFakeResult(expected)

        val result: List<SearchEntity> = useCase.invoke(query).first()

        assertEquals(expected, result)
    }

    class FakeSearchRepository : IGithubRepositoriesRepository {
        private var result: List<SearchEntity> = emptyList()

        fun setFakeResult(list: List<SearchEntity>) {
            result = list
        }

        override suspend fun searchForRepositories(query: String): Flow<List<SearchEntity>> {
            return flowOf(result)
        }
    }
}
