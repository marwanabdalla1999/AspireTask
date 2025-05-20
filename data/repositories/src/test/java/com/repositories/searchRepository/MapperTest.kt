package com.repositories.searchRepository

import com.githubRepositoriesService.responses.SearchResponse
import com.repositories.githubRepositoriesRepository.mappers.toSearchForTrendingGithubRepoEntity
import com.searchUseCases.entities.SearchEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperTest {

    @Test
    fun `toSearchForTrendingGithubRepoEntity maps correctly with all fields`() {
        val item = SearchResponse.Item(
            full_name = "JetBrains/compose-multiplatform",
            owner = SearchResponse.Item.Owner(
                login = "JetBrains",
                avatar_url = "https://avatar.url"
            ),
            description = "A modern declarative UI framework",
            language = "Kotlin",
            stargazers_count = 999
        )

        val expected = SearchEntity(
            repositoryName = "JetBrains/compose-multiplatform",
            repositoryOwnerName = "JetBrains",
            repositoryOwnerAvatarUrl = "https://avatar.url",
            description = "A modern declarative UI framework",
            language = "Kotlin",
            starsCount = 999
        )

        val actual = item.toSearchForTrendingGithubRepoEntity()
        assertEquals(expected, actual)
    }

    @Test
    fun `toSearchForTrendingGithubRepoEntity applies default values when fields are null`() {
        val item = SearchResponse.Item(
            full_name = null,
            owner = null,
            description = null,
            language = null,
            stargazers_count = null
        )

        val expected = SearchEntity(
            repositoryName = "",
            repositoryOwnerName = "",
            repositoryOwnerAvatarUrl = "",
            description = "",
            language = null,
            starsCount = 0
        )

        val actual = item.toSearchForTrendingGithubRepoEntity()
        assertEquals(expected, actual)
    }
}
