package com.adesso.ghrepolist.domain

import com.adesso.ghrepolist.data.remote.model.GitHubRepoModelItem
import com.adesso.ghrepolist.data.remote.model.RepoOwnerModel
import com.adesso.ghrepolist.data.repository.GitHubRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchGitHubRepoUseCaseTest {

    @MockK
    lateinit var githubRepository: GitHubRepository
    lateinit var fetchGitHubRepoUseCase: FetchGitHubRepoUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        this.fetchGitHubRepoUseCase = FetchGitHubRepoUseCase(githubRepository)
    }

    @Test
    fun testExecute_Positive() = runBlocking {
        coEvery { githubRepository.fetchUserGitHubRepo(any()) } returns listOf(
            GitHubRepoModelItem(
                "repo1",
                RepoOwnerModel("user1", "userUrl1"),
                10,
                20,
                false
            )
        )

        fetchGitHubRepoUseCase.run(FetchGitHubRepoUseCase.Params("asd"))

        coVerify {
            githubRepository.fetchUserGitHubRepo("asd")
        }
    }

}