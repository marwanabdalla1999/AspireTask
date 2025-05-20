package com.githubRepositoriesService.githubRepositoriesDataSource

import com.githubRepositoriesService.apiService.GithubRepositoriesApiService
import com.githubRepositoriesService.responses.SearchResponse
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class RemoteGithubRepositoriesDataSourceImplTest {

    private val apiService: GithubRepositoriesApiService = mockk()
    private lateinit var dataSource: RemoteGithubRepositoriesDataSourceImpl

    @Before
    fun setup() {
        dataSource = RemoteGithubRepositoriesDataSourceImpl(apiService)
    }

    @Test
    fun `searchForRepositories should return SearchResponse when API call succeeds`() = runTest {
        val query = "compose"
        val expected = SearchResponse(items = listOf(), total_count = 0, incomplete_results = false)

        coEvery { apiService.searchRepositories(query) } returns Response.success(expected)

        val result = dataSource.searchForRepositories(query)

        assertEquals(expected, result)
    }
}


