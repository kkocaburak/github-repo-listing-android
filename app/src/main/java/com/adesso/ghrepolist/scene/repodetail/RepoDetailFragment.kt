package com.adesso.ghrepolist.scene.repodetail

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.adesso.ghrepolist.R
import com.adesso.ghrepolist.base.BaseFragment
import com.adesso.ghrepolist.databinding.FragmentRepoDetailBinding

class RepoDetailFragment :
    BaseFragment<RepoDetailViewModel, FragmentRepoDetailBinding>() {

    private val args by navArgs<RepoDetailFragmentArgs>()

    override val layoutId = R.layout.fragment_repo_detail

    override fun initialize() {
        viewModel.initialize(args.repoModelItem)
    }
}