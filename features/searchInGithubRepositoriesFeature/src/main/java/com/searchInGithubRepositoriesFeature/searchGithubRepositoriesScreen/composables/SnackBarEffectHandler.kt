package com.searchInGithubRepositoriesFeature.searchGithubRepositoriesScreen.composables

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.searchInGithubRepositoriesFeature.SearchGithubRepositoriesContract
import kotlinx.coroutines.flow.Flow

@Composable
fun SnackBarEffectHandler(
  effects: Flow<SearchGithubRepositoriesContract.RepoUiEffect>,
  hostState: SnackbarHostState
) {
  LaunchedEffect(Unit) {
    effects.collect { effect ->
      if (effect is SearchGithubRepositoriesContract.RepoUiEffect.ShowError.GeneralError) {
        effect.message?.let { hostState.showSnackbar(message = it) }
      }
    }
  }
}