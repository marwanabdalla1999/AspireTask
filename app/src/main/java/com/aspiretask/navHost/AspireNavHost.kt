package com.aspiretask.navHost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core_navigations.SearchGithubRepositories
import com.searchInGithubRepositoriesFeature.searchGithubRepositoriesScreen.githubRepositoriesSearchRoute


@Composable
fun AspireNavHost(navController: NavHostController, modifier: Modifier = Modifier){

    NavHost(
        navController = navController,
        startDestination = SearchGithubRepositories,
        modifier = modifier
    ) {
        githubRepositoriesSearchRoute()

    }
}