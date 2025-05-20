package com.githubRepositoriesService.apiService

import com.coreNetwork.NetworkConstants.Search.END_POINT
import com.coreNetwork.NetworkConstants.Search.Q
import com.coreNetwork.NetworkConstants.Search.SORT
import com.coreNetwork.NetworkConstants.Search.STARS
import com.githubRepositoriesService.responses.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubRepositoriesApiService {
    @GET(END_POINT)
    suspend fun searchRepositories(
        @Query(Q) query: String,
        @Query(SORT) sort: String = STARS
    ): Response<SearchResponse?>
}