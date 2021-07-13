package com.adesso.ghrepolist.scene.repolist

import com.adesso.ghrepolist.R
import com.adesso.ghrepolist.base.BaseListAdapter
import com.adesso.ghrepolist.data.remote.model.GitHubRepoModelItem
import com.adesso.ghrepolist.databinding.ItemGitHubRepoBinding
import com.adesso.ghrepolist.internal.extension.executeAfter

interface GitHubRepoItemListener {

    fun onRepoItemClick(gitHubRepoModelItem: GitHubRepoModelItem)

    fun onFavoriteButtonClick(gitHubRepoModelItem: GitHubRepoModelItem)
}

class RepoListAdapter(private val gitHubRepoItemListener: GitHubRepoItemListener) :
    BaseListAdapter<ItemGitHubRepoBinding, GitHubRepoModelItem>() {

    override val layoutRes: Int get() = R.layout.item_git_hub_repo

    override fun bind(binding: ItemGitHubRepoBinding, item: GitHubRepoModelItem) {
        binding.executeAfter {
            gitHubRepo = item
            listener = gitHubRepoItemListener
        }
    }
}