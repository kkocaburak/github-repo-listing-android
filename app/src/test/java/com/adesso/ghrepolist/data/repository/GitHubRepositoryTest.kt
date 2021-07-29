package com.adesso.ghrepolist.data.repository

import com.adesso.ghrepolist.data.remote.datasource.GitHubRepoRemoteDataSource
import com.adesso.ghrepolist.data.remote.model.GitHubRepoModelItem
import com.adesso.ghrepolist.data.remote.model.GitHubRepoResponseModel
import com.adesso.ghrepolist.data.remote.model.RepoOwnerModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GitHubRepositoryTest {

    @MockK
    lateinit var remoteDataSource: GitHubRepoRemoteDataSource
    lateinit var gitHubRepository: GitHubRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        this.gitHubRepository = GitHubRepository(remoteDataSource)
    }

    @Test
    fun `when fetchUserGitHubRepo response object should match with mock repsonse` () = runBlocking {
        val exampleRepoModel = GitHubRepoModelItem(
            "repo1",
            RepoOwnerModel("user1", "userUrl1"),
            10,
            20,
            false
        )

        val exampleResponseModel = GitHubRepoResponseModel()
        exampleResponseModel.add(
            exampleRepoModel
        )

        coEvery { remoteDataSource.fetchUserGitHubRepo("asd") } returns exampleResponseModel

        val response = gitHubRepository.fetchUserGitHubRepo("asd")

        val actualResponseModel = GitHubRepoResponseModel()
        actualResponseModel.add(
            GitHubRepoModelItem(
                "repo1",
                RepoOwnerModel("user1", "userUrl1"),
                10,
                20,
                false
            )
        )

        assertEquals(response[0].repoName, "repo1")
        assertEquals(response[0].repoOwner, RepoOwnerModel("user1", "userUrl1"))
        assertEquals(response[0].repoStarCount, 10)
        assertEquals(response[0].openIssuesCount, 20)
        assertEquals(response[0].isFavorite, false)
        assertEquals(response, actualResponseModel)
    }
}