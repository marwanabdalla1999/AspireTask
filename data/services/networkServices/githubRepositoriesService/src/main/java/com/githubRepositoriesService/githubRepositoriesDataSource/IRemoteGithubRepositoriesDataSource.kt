package com.githubRepositoriesService.githubRepositoriesDataSource

import com.githubRepositoriesService.responses.SearchResponse

interface IRemoteGithubRepositoriesDataSource {

   suspend fun searchForRepositories(query:String): SearchResponse?
}