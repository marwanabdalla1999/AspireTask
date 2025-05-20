package com.repositories.searchRepository

import com.githubRepositoriesService.githubRepositoriesDataSource.IRemoteGithubRepositoriesDataSource
import com.githubRepositoriesService.responses.SearchResponse
import com.repositories.githubRepositoriesRepository.GithubRepositoriesRepositoryImpl
import com.repositories.githubRepositoriesRepository.mappers.toSearchForTrendingGithubRepoEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

class GithubRepositoriesRepositoryImplTest {

    private lateinit var repository: GithubRepositoriesRepositoryImpl
    private val remoteDataSource: IRemoteGithubRepositoriesDataSource = mockk()

    @Before
    fun setUp() {
        repository = GithubRepositoriesRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `searchForRepositories emits mapped data when response is valid`() = runTest {
        val query = "android"
        val response = SearchResponse(
            total_count = 1,
            incomplete_results = false,
            items = listOf(
                SearchResponse.Item(
                    full_name = "JetBrains/compose",
                    owner = SearchResponse.Item.Owner(
                        login = "JetBrains",
                        avatar_url = "https://avatar.com/jb"
                    ),
                    description = "Compose for Desktop",
                    language = "Kotlin",
                    stargazers_count = 1000,
                )
            )
        )

        coEvery { remoteDataSource.searchForRepositories(query) } returns response

        val result = repository.searchForRepositories(query).first()

        val expected = listOf(response.items!!.first()?.toSearchForTrendingGithubRepoEntity())
        assertEquals(expected, result)
    }

    @Test
    fun `searchForRepositories throws exception when response is null`() = runTest {
        val query = "compose"
        coEvery { remoteDataSource.searchForRepositories(query) } returns null

        try {
            repository.searchForRepositories(query).first()
            fail("Expected Exception was not thrown")
        } catch (e: Exception) {
            assertEquals("Unknown Error", e.message)
        }
    }

    @Test
    fun `searchForRepositories filters null items`() = runTest {
        val query = "kotlin"
        val validItem = SearchResponse.Item(
            full_name = "JetBrains/kotlin",
            owner = SearchResponse.Item.Owner(
                login = "JetBrains",
                avatar_url = "https://avatar.com/jb"
            ),
            description = "Kotlin programming language",
            language = "Kotlin",
            stargazers_count = 5000
        )

        val response = SearchResponse(
            total_count = 2,
            incomplete_results = false,
            items = listOf(validItem, null)
        )

        coEvery { remoteDataSource.searchForRepositories(query) } returns response

        val result = repository.searchForRepositories(query).first()

        val expected = listOf(validItem.toSearchForTrendingGithubRepoEntity())
        assertEquals(expected, result)
    }
}