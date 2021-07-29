package com.adesso.ghrepolist.scene.repolist

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adesso.ghrepolist.data.remote.model.GitHubRepoModelItem
import com.adesso.ghrepolist.data.remote.model.RepoOwnerModel
import com.adesso.ghrepolist.domain.FetchGitHubRepoUseCase
import com.adesso.ghrepolist.internal.util.functional.Either
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class RepoListViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var fetchGitHubRepoUseCase: FetchGitHubRepoUseCase

    @MockK
    private lateinit var application: Application

    lateinit var repoListViewModel: RepoListViewModel

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)
        this.repoListViewModel = RepoListViewModel(application, fetchGitHubRepoUseCase)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when onSubmitButtonClicked should call fetchGitHubRepoList`() = runBlocking {
        val expected = listOf(
                GitHubRepoModelItem(
                        "repo1",
                        RepoOwnerModel("user1", "userUrl1"),
                        10,
                        20,
                        false
                )
        )

        coEvery {
            fetchGitHubRepoUseCase.run(any())
        } returns Either.Success(expected)

        repoListViewModel.userName = "asd"
        repoListViewModel.onSubmitButtonClicked()

        val actual = repoListViewModel.repoModelItemList.value

        Assert.assertEquals(expected,actual)
    }

}