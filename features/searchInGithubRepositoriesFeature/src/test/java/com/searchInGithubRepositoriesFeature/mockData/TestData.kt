package com.searchInGithubRepositoriesFeature.mockData

import com.searchUseCases.entities.SearchEntity

object TestData {
    fun fakeGithubRepo() = SearchEntity(
        repositoryName = "Jetpack Compose",
        repositoryOwnerName = "Android",
        description = "Modern toolkit for building UI",
        starsCount = 12345,
        language = "Kotlin",
        repositoryOwnerAvatarUrl = "https://github.com/android/compose"
    )
}
