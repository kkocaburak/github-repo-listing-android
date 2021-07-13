package com.adesso.ghrepolist.data.remote.api

import com.adesso.ghrepolist.data.remote.model.GitHubRepoResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET(USER_REPO)
    suspend fun fetchUserGitHubRepo(@Path(USER) user: String): GitHubRepoResponseModel

    companion object {
        const val USER = "user"
        const val USER_REPO = "users/{$USER}/repos"
    }

}