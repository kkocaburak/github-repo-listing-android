package com.adesso.ghrepolist.domain

import com.adesso.ghrepolist.data.remote.model.GitHubRepoModelItem
import com.adesso.ghrepolist.data.repository.GitHubRepository
import com.adesso.ghrepolist.internal.util.UseCase
import javax.inject.Inject

class FetchGitHubRepoUseCase @Inject constructor(
    private val repository: GitHubRepository
) : UseCase<List<GitHubRepoModelItem>, FetchGitHubRepoUseCase.Params>() {

    override suspend fun buildUseCase(params: Params) = repository.fetchUserGitHubRepo(params.userName)

    data class Params(val userName: String)
}