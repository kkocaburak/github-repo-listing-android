package com.adesso.ghrepolist.scene.repolist

import android.app.Application
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adesso.ghrepolist.base.BaseAndroidViewModel
import com.adesso.ghrepolist.data.remote.model.GitHubRepoModelItem
import com.adesso.ghrepolist.domain.FetchGitHubRepoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class RepoListViewModel @Inject constructor(
    application: Application,
    private val fetchGitHubRepoUseCase: FetchGitHubRepoUseCase
) : BaseAndroidViewModel(application), GitHubRepoItemListener {

    private val _repoModelList = MutableLiveData<List<GitHubRepoModelItem>>()
    val repoModelItemList: LiveData<List<GitHubRepoModelItem>> get() = _repoModelList

    var userName: String = ""

    fun onSubmitButtonClicked() {
        fetchGitHubRepoList(userName)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun fetchGitHubRepoList(userName: String) = viewModelScope.launch {
        fetchGitHubRepoUseCase
            .run(FetchGitHubRepoUseCase.Params(userName))
            .either(::handleFailure, ::postGitHubRepoList)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun postGitHubRepoList(gitHubRepoList: List<GitHubRepoModelItem>) {
        _repoModelList.value = gitHubRepoList
        println(gitHubRepoList)
    }

    override fun onRepoItemClick(gitHubRepoModelItem: GitHubRepoModelItem) {
        navigateToRelatedScreen(gitHubRepoModelItem)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun navigateToRelatedScreen(gitHubRepoModelItem: GitHubRepoModelItem) {
        navigate(
            RepoListFragmentDirections.actionRepoListFragmentToRepoDetailFragment(
                gitHubRepoModelItem
            )
        )
    }

    override fun onFavoriteButtonClick(gitHubRepoModelItem: GitHubRepoModelItem) {
        // save repo to favorites
    }
}