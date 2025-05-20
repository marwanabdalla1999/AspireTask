package com.searchInGithubRepositoriesFeature.searchGithubRepositoriesScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.core_ui.models.githubRepositoriesModels.AppRepositoriesModel
import com.core_ui.commonUi.RepoItem

@Composable
fun GithubRepositoriesSearchContent(repositories:List<AppRepositoriesModel>, modifier: Modifier = Modifier){
    LazyColumn(
        modifier = modifier

    ) {
        items(repositories) { repository ->
            RepoItem(repository)
        }
    }
}