package com.adesso.ghrepolist.internal.injection.module

import com.adesso.ghrepolist.internal.injection.scope.MainScope
import com.adesso.ghrepolist.scene.main.MainActivity
import com.adesso.ghrepolist.scene.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @MainScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}