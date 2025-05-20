package com.searchInGithubRepositoriesFeature.searchGithubRepositoriesScreen.composables

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.repositoriesfeature.R
import com.searchInGithubRepositoriesFeature.SearchGithubRepositoriesContract
import kotlinx.coroutines.flow.Flow

@Composable
fun SnackBarEffectHandler(
  effects: Flow<SearchGithubRepositoriesContract.RepoUiEffect>,
  hostState: SnackbarHostState
) {
  val context = LocalContext.current
  LaunchedEffect(Unit) {
    effects.collect { effect ->
      if (effect is SearchGithubRepositoriesContract.RepoUiEffect.ShowError) {
        hostState.showSnackbar(context.getString(R.string.there_some_thing_went_wrong_please_check_your_internet_connection))
      }
    }
  }
}