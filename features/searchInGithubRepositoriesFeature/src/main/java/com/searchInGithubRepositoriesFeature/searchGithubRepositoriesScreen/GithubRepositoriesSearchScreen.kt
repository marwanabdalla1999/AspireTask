package com.searchInGithubRepositoriesFeature.searchGithubRepositoriesScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.core_ui.commonUi.SearchView
import com.searchInGithubRepositoriesFeature.SearchGithubRepositoriesContract
import com.searchInGithubRepositoriesFeature.searchGithubRepositoriesScreen.composables.SnackBarEffectHandler
import com.example.repositoriesfeature.R
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubRepositoriesSearchScreen(
    state: State<SearchGithubRepositoriesContract.RepoUiState>,
    effects: Flow<SearchGithubRepositoriesContract.RepoUiEffect>,
    setEvent: (SearchGithubRepositoriesContract.RepoUiEvent) -> Unit = {},
    onDarkModeClicked: () -> Unit
) {

    val snackBarHostState = remember { SnackbarHostState() }

    SnackBarEffectHandler(effects, snackBarHostState)

    Scaffold(topBar = {
        TopAppBar(title = { Text("GitHub Repos") }, actions = {
            SearchView(query = state.value.query,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                onQueryChanged = {
                    setEvent(
                        SearchGithubRepositoriesContract.RepoUiEvent.OnEnterQuery(it)
                    )
                },
                onQuerySubmitted = {
                    setEvent(
                        SearchGithubRepositoriesContract.RepoUiEvent.OnSearchClicked(it)
                    )
                })
        })
    }, content = { padding ->
        if (state.value.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            GithubRepositoriesSearchContent(
                repositories = state.value.data, modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            )
        }
    }, floatingActionButton = {
        Button(onClick = {
            onDarkModeClicked()
        }) {
            Text(text = stringResource(R.string.Change_theme))
        }
    }, snackbarHost = { SnackbarHost(snackBarHostState) })
}
