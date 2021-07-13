package com.adesso.ghrepolist.internal.injection.component

import com.adesso.ghrepolist.internal.injection.DaggerApplication
import com.adesso.ghrepolist.internal.injection.module.ActivitiesModule
import com.adesso.ghrepolist.internal.injection.module.AppModule
import com.adesso.ghrepolist.internal.injection.module.FragmentsModule
import com.adesso.ghrepolist.internal.injection.module.NetworkModule
import com.adesso.ghrepolist.internal.injection.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        FragmentsModule::class,
        ActivitiesModule::class
    ]
)
internal interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DaggerApplication>()
}
