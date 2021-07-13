package com.adesso.ghrepolist.data.remote.datasource

import com.adesso.ghrepolist.data.remote.api.GitHubService
import com.adesso.ghrepolist.data.remote.model.GitHubRepoModelItem
import com.adesso.ghrepolist.data.remote.model.GitHubRepoResponseModel
import com.adesso.ghrepolist.data.remote.model.RepoOwnerModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GitHubRepoRemoteDataSourceTest {

    @MockK
    lateinit var service: GitHubService
    lateinit var gitHubRepoRemoteDataSource: GitHubRepoRemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        this.gitHubRepoRemoteDataSource = GitHubRepoRemoteDataSource(service)
    }

    @Test
    fun `when fetchUserGitHubRepo called should return GitHubRepoResponseModel`() = runBlocking {
        val exampleRepoModel = GitHubRepoModelItem(
            "repo1",
            RepoOwnerModel("user1", "userUrl1"),
            10,
            20,
            false
        )

        val exampleResponseModel = GitHubRepoResponseModel()
        exampleResponseModel.add(exampleRepoModel)

        coEvery { gitHubRepoRemoteDataSource.fetchUserGitHubRepo(any()) } returns exampleResponseModel

        Assert.assertEquals(
            "repo1",
            gitHubRepoRemoteDataSource.fetchUserGitHubRepo("asd")[0].repoName
        )
    }

    @Test
    fun `when fetchUserGitHubRepo called should call service fetchUserGitHubRepo method`() = runBlocking {
        coEvery { gitHubRepoRemoteDataSource.fetchUserGitHubRepo(any()) } returns mockk()

        gitHubRepoRemoteDataSource.fetchUserGitHubRepo("asd")

        coVerify { service.fetchUserGitHubRepo(any()) }
    }

}