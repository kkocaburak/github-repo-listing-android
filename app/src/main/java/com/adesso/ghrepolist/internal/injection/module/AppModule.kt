package com.adesso.ghrepolist.internal.injection.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.adesso.ghrepolist.internal.injection.DaggerApplication
import com.adesso.ghrepolist.internal.util.ResourceProvider
import com.adesso.ghrepolist.internal.util.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class AppModule {

    @Provides
    @Singleton
    internal fun provideApplicationContext(application: DaggerApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    internal fun provideApplication(application: DaggerApplication): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(
            application.applicationContext
        )
    }

    @Provides
    @Singleton
    fun provideResourceProvider(application: Application): ResourceProvider {
        return ResourceProviderImpl(application.applicationContext)
    }
}