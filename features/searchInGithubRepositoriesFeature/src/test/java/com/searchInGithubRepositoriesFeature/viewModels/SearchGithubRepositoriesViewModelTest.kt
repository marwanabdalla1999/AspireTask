package com.searchInGithubRepositoriesFeature.viewModels

import app.cash.turbine.test
import com.searchInGithubRepositoriesFeature.SearchGithubRepositoriesContract
import com.searchInGithubRepositoriesFeature.SearchGithubRepositoriesViewModel
import com.searchInGithubRepositoriesFeature.mappers.toAppRepositoriesModel
import com.searchInGithubRepositoriesFeature.mockData.TestData
import com.searchUseCases.usecases.searchWithQueryUseCase.ISearchWithQueryUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
class SearchGithubRepositoriesViewModelTest {

    private lateinit var viewModel: SearchGithubRepositoriesViewModel
    private val searchWithQueryUseCase: ISearchWithQueryUseCase = mockk()


    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = SearchGithubRepositoriesViewModel(searchWithQueryUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `OnEnterQuery should update the state with new query`() = runTest {
        val newQuery = "android"
        viewModel.setEvent(SearchGithubRepositoriesContract.RepoUiEvent.OnEnterQuery(newQuery))
        advanceUntilIdle()
        assertEquals(newQuery, viewModel.viewState.value.query)
    }

    @Test
    fun `OnSearchClicked should update loading state and return success`() = runTest {
        val query = "compose"
        val domainList = listOf(TestData.fakeGithubRepo())
        val uiList = domainList.map { it.toAppRepositoriesModel() }
        coEvery { searchWithQueryUseCase(query) } returns flowOf(domainList)

        viewModel.viewState.test {
            skipItems(1)
            viewModel.setEvent(SearchGithubRepositoriesContract.RepoUiEvent.OnSearchClicked(query))
            advanceUntilIdle()
            val loadingState = awaitItem()
            assertTrue("Expected loading state", loadingState.isLoading)

            val successState = awaitItem()
            assertFalse("Expected not loading", successState.isLoading)
            assertEquals("Expected mapped data list", uiList, successState.data)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `OnSearchClicked should return error effect when use case fails`() = runTest {
        val query = "error-case"
        val exception = RuntimeException("API failure")

        coEvery { searchWithQueryUseCase(query) } returns flow { throw exception }

        val job = launch {
            viewModel.effect.test {
                viewModel.setEvent(
                    SearchGithubRepositoriesContract.RepoUiEvent.OnSearchClicked(
                        query
                    )
                )

                val effect = awaitItem()
                assertTrue(effect is SearchGithubRepositoriesContract.RepoUiEffect.ShowError)
                assertEquals(
                    "API failure",
                    (effect as SearchGithubRepositoriesContract.RepoUiEffect.ShowError).message
                )

                cancelAndIgnoreRemainingEvents()
            }
        }

        job.join()
    }
}
