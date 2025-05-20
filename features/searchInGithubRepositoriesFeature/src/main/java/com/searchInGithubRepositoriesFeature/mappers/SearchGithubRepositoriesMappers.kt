package com.searchInGithubRepositoriesFeature.mappers

import com.core_ui.models.githubRepositoriesModels.AppRepositoriesModel
import com.searchUseCases.entities.SearchEntity


fun SearchEntity.toAppRepositoriesModel() = AppRepositoriesModel(
    repositoryName = this.repositoryName,
    repositoryOwnerName = this.repositoryOwnerName,
    repositoryOwnerAvatarUrl = this.repositoryOwnerAvatarUrl,
    starsCount = this.starsCount,
    description = this.description,
    language = this.language
)
