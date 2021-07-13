package com.adesso.ghrepolist.data.repository

import com.adesso.ghrepolist.data.remote.datasource.GitHubRepoRemoteDataSource
import com.adesso.ghrepolist.data.remote.model.GitHubRepoModelItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepository @Inject constructor(
    private val remoteDataSource: GitHubRepoRemoteDataSource
) {
    suspend fun fetchUserGitHubRepo(userName: String): List<GitHubRepoModelItem> {
        return remoteDataSource.fetchUserGitHubRepo(userName)
    }
}
