package com.adesso.ghrepolist.data.remote.datasource

import com.adesso.ghrepolist.data.remote.BaseRemoteDataSource
import com.adesso.ghrepolist.data.remote.api.GitHubService
import com.adesso.ghrepolist.data.remote.model.GitHubRepoResponseModel
import javax.inject.Inject

class GitHubRepoRemoteDataSource @Inject constructor(
    private val service: GitHubService
) : BaseRemoteDataSource() {

    suspend fun fetchUserGitHubRepo(userName: String): GitHubRepoResponseModel = invoke {
        service.fetchUserGitHubRepo(userName)
    }
}