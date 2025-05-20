package com.searchInGithubRepositoriesFeature

import androidx.lifecycle.viewModelScope
import com.core_ui.base.BaseViewModel
import com.searchUseCases.usecases.searchWithQueryUseCase.ISearchWithQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.searchInGithubRepositoriesFeature.SearchGithubRepositoriesContract.RepoUiEvent
import com.searchInGithubRepositoriesFeature.SearchGithubRepositoriesContract.RepoUiState
import com.searchInGithubRepositoriesFeature.SearchGithubRepositoriesContract.RepoUiEffect
import com.searchInGithubRepositoriesFeature.mappers.toAppRepositoriesModel
import kotlinx.coroutines.Job


@HiltViewModel
class SearchGithubRepositoriesViewModel @Inject constructor(
    private val searchGithubReposUseCase: ISearchWithQueryUseCase
) : BaseViewModel<RepoUiState, RepoUiEvent, RepoUiEffect>() {
    private var job: Job? = null
    override fun setInitialState() = RepoUiState(data = emptyList())


    private fun searchRepositories(query: String) {
        job?.cancel()
        job = viewModelScope.launch {
            setState { copy(isLoading = true) }
            searchGithubReposUseCase(query = query).launchAndCollectResult(onSuccess = { reposList ->
                val reposUiList = reposList.map { it.toAppRepositoriesModel() }
                setState { copy(isLoading = false, data = reposUiList) }
            }, onError = {
                setState { copy(isLoading = false) }
                setEffect { RepoUiEffect.ShowError.GeneralError }
            })
        }
    }


    override fun handleEvents(event: RepoUiEvent) {
        when (event) {
            is RepoUiEvent.OnSearchClicked -> {
                searchRepositories(event.query)
            }

            is RepoUiEvent.OnEnterQuery -> setState {
                copy(
                    query = event.newQuery
                )
            }
        }
    }
}
