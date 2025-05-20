package com.searchUseCases.entities

data class SearchEntity(
    val repositoryName: String,
    val repositoryOwnerName: String,
    val repositoryOwnerAvatarUrl: String,
    val starsCount: Int,
    val description: String,
    val language: String? = null
)
