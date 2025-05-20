package com.core_ui.models.githubRepositoriesModels

data class AppRepositoriesModel(
    val repositoryName: String,
    val repositoryOwnerName: String,
    val repositoryOwnerAvatarUrl: String,
    val starsCount: Int,
    val description: String,
    val language: String?=null
)
