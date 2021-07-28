package com.adesso.ghrepolist.base

import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.adesso.ghrepolist.R
import com.adesso.ghrepolist.internal.util.functional.lazyThreadSafetyNone
import dagger.android.support.DaggerAppCompatActivity
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseActivity<VM : BaseAndroidViewModel> : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var loadingProgressBar: ProgressBar? = null
    private var loadingContainer: FrameLayout? = null

    protected val viewModel by lazyThreadSafetyNone {
        val persistentViewModelClass = (javaClass.genericSuperclass as ParameterizedType)
                .actualTypeArguments[0] as Class<VM>
        return@lazyThreadSafetyNone ViewModelProvider(this, viewModelFactory)
                .get(persistentViewModelClass)
    }

    fun initializeActivity() {
        loadingProgressBar = findViewById(R.id.main_progress_bar_loading)
    }

    fun showProgress() {
        loadingProgressBar?.visibility = View.VISIBLE
    }

    fun hideProgress() {
        loadingProgressBar?.visibility = View.GONE
    }
}
