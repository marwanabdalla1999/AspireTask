package com.repositories.githubRepositoriesRepository.mappers

import com.searchUseCases.entities.SearchEntity
import com.githubRepositoriesService.responses.SearchResponse

fun SearchResponse.Item.toSearchForTrendingGithubRepoEntity() =
    SearchEntity(
        repositoryName = this.full_name ?: "",
        repositoryOwnerName = this.owner?.login ?: "",
        repositoryOwnerAvatarUrl = this.owner?.avatar_url ?: "",
        description = this.description ?: "",
        language = this.language,
        starsCount = this.stargazers_count ?: 0
    )