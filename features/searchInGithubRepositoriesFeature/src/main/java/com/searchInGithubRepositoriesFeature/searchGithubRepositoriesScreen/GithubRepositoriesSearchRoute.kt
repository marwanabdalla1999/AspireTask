package com.searchInGithubRepositoriesFeature.searchGithubRepositoriesScreen

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.core_navigations.SearchGithubRepositories
import com.core_ui.theme.LocalToggleTheme
import com.searchInGithubRepositoriesFeature.SearchGithubRepositoriesViewModel

fun NavGraphBuilder.githubRepositoriesSearchRoute(){
    composable<SearchGithubRepositories> {
       val  viewModel: SearchGithubRepositoriesViewModel = hiltViewModel()
        GithubRepositoriesSearchScreen(
            setEvent = viewModel::setEvent,
            state = viewModel.viewState.collectAsStateWithLifecycle(),
            effects = viewModel.effect,
            onDarkModeClicked = LocalToggleTheme.current
            )
    }

}