package com.adesso.ghrepolist.internal.injection.module

import com.adesso.ghrepolist.internal.injection.scope.GitHubRepoScope
import com.adesso.ghrepolist.scene.repodetail.RepoDetailFragment
import com.adesso.ghrepolist.scene.repolist.RepoListFragment
import com.adesso.ghrepolist.scene.repolist.RepoListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentsModule {

    @GitHubRepoScope
    @ContributesAndroidInjector(modules = [RepoListModule::class])
    abstract fun contributeRepoListFragment(): RepoListFragment

    @GitHubRepoScope
    @ContributesAndroidInjector
    abstract fun contributeRepoDetailFragment(): RepoDetailFragment
}