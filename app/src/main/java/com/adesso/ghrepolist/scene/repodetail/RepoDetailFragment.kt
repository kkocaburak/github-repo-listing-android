package com.adesso.ghrepolist.scene.repodetail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.adesso.ghrepolist.R
import com.adesso.ghrepolist.base.BaseFragment
import com.adesso.ghrepolist.databinding.FragmentRepoDetailBinding
import com.google.android.material.appbar.MaterialToolbar

class RepoDetailFragment :
    BaseFragment<RepoDetailViewModel, FragmentRepoDetailBinding>() {

    private val args by navArgs<RepoDetailFragmentArgs>()

    override val layoutId = R.layout.fragment_repo_detail

    override fun initialize() {
        viewModel.initialize(args.repoModelItem)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerToolbarOnClicks()
    }

    private fun registerToolbarOnClicks() {
        view?.let {
            val toolbar: MaterialToolbar = it.findViewById(R.id.repo_detail_toolbar)
            toolbar.setNavigationOnClickListener {
                viewModel.onBackIconPressed()
            }

            toolbar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.favorite -> {
                        // on favorite icon pressed
                        true
                    }
                    else -> false
                }
            }
        }
    }
}