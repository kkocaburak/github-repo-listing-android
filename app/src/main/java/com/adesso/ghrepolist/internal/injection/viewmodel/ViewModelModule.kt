package com.adesso.ghrepolist.internal.injection.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adesso.ghrepolist.scene.main.MainViewModel
import com.adesso.ghrepolist.scene.repodetail.RepoDetailViewModel
import com.adesso.ghrepolist.scene.repolist.RepoListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoListViewModel::class)
    abstract fun bindsRepoListViewModel(viewModel: RepoListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepoDetailViewModel::class)
    abstract fun bindsRepoDetailViewModel(viewModel: RepoDetailViewModel): ViewModel

}