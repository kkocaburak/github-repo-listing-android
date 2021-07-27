package com.adesso.ghrepolist.scene.repolist

import com.adesso.ghrepolist.R
import com.adesso.ghrepolist.base.BaseFragment
import com.adesso.ghrepolist.databinding.FragmentRepoListBinding


class RepoListFragment :
    BaseFragment<RepoListViewModel, FragmentRepoListBinding>() {

    override val layoutId: Int get() = R.layout.fragment_repo_list

    override fun initialize() {
        binder.gitHubRepoAdapter = RepoListAdapter(viewModel)
    }
}