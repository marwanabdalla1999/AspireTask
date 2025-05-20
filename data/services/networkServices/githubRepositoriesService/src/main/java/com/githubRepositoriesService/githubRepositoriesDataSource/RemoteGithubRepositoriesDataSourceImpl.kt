package com.githubRepositoriesService.githubRepositoriesDataSource

import com.coreNetwork.NetworkHelper
import com.githubRepositoriesService.apiService.GithubRepositoriesApiService
import com.githubRepositoriesService.responses.SearchResponse

class RemoteGithubRepositoriesDataSourceImpl (private val githubRepositoriesApiService: GithubRepositoriesApiService):
    IRemoteGithubRepositoriesDataSource {
    override suspend fun searchForRepositories(query: String): SearchResponse? {
        return NetworkHelper.processCall {
            githubRepositoriesApiService.searchRepositories(query)
        }
    }


}