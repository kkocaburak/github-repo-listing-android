package com.adesso.ghrepolist.scene.repolist

import android.app.Application
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adesso.ghrepolist.data.remote.model.GitHubRepoModelItem
import com.adesso.ghrepolist.data.remote.model.RepoOwnerModel
import com.adesso.ghrepolist.domain.FetchGitHubRepoUseCase
import com.adesso.ghrepolist.internal.popup.PopupModel
import com.adesso.ghrepolist.internal.util.functional.Either
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class RepoListViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var fetchGitHubRepoUseCase: FetchGitHubRepoUseCase
    lateinit var repoListViewModel: RepoListViewModel
    private lateinit var application: Application

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        application = ApplicationProvider.getApplicationContext() as Application
        this.repoListViewModel = RepoListViewModel(application, fetchGitHubRepoUseCase)
    }

    @Test
    fun `when onSubmitButtonClicked should call fetchGitHubRepoList`() = runBlocking {
        val list = listOf(
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
        } returns Either.Success(list)

        repoListViewModel.userName = "asd"
        repoListViewModel.onSubmitButtonClicked()

        coVerify { repoListViewModel.fetchGitHubRepoList("asd") }
    }

}