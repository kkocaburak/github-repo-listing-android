package com.adesso.ghrepolist.scene.repodetail

import android.app.Application
import com.adesso.ghrepolist.base.BaseAndroidViewModel
import com.adesso.ghrepolist.data.remote.model.GitHubRepoModelItem
import com.adesso.ghrepolist.internal.popup.PopupModel
import javax.inject.Inject

class RepoDetailViewModel @Inject constructor(
    application: Application
) : BaseAndroidViewModel(application) {
    private lateinit var _gitHubRepoModelItem: GitHubRepoModelItem
    val gitHubRepoModelItem: GitHubRepoModelItem get() = _gitHubRepoModelItem

    fun initialize(gitHubRepoModelItem: GitHubRepoModelItem) {
        _gitHubRepoModelItem = gitHubRepoModelItem
    }

    fun showFavoriteInteractionPopup(title: String, message: String) {
        navigate(PopupModel(title = title, message = message))
    }

    fun onBackIconPressed() {
        navigateBack()
    }
}