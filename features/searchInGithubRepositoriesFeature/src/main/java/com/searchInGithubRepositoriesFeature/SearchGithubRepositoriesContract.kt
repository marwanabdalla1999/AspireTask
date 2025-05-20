package com.searchInGithubRepositoriesFeature

import com.core_ui.base.ViewEvent
import com.core_ui.base.ViewSideEffect
import com.core_ui.base.ViewState
import com.core_ui.models.githubRepositoriesModels.AppRepositoriesModel

sealed class SearchGithubRepositoriesContract {


    data class RepoUiState(val isLoading: Boolean = false, val query: String = "" ,val data: List<AppRepositoriesModel>):
        ViewState


    sealed interface RepoUiEvent : ViewEvent {
        data class OnSearchClicked(val query: String) : RepoUiEvent
        data class OnEnterQuery(val newQuery: String) : RepoUiEvent
    }

    sealed interface RepoUiEffect : ViewSideEffect {
        sealed class ShowError : RepoUiEffect {
            data object GeneralError : ShowError()
        }
    }


}