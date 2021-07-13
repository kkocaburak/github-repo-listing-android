package com.adesso.ghrepolist.base

import androidx.lifecycle.ViewModelProvider
import com.adesso.ghrepolist.internal.util.functional.lazyThreadSafetyNone
import dagger.android.support.DaggerAppCompatActivity
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseActivity<VM : BaseAndroidViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val viewModel by lazyThreadSafetyNone {
        val persistentViewModelClass = (javaClass.genericSuperclass as ParameterizedType)
                .actualTypeArguments[0] as Class<VM>
        return@lazyThreadSafetyNone ViewModelProvider(this, viewModelFactory)
                .get(persistentViewModelClass)
    }
}
