package com.adesso.ghrepolist.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.adesso.ghrepolist.BR
import com.adesso.ghrepolist.internal.extension.observeNonNull
import com.adesso.ghrepolist.internal.extension.showPopup
import com.adesso.ghrepolist.internal.util.functional.lazyThreadSafetyNone
import com.adesso.ghrepolist.navigation.NavigationCommand
import dagger.android.support.DaggerFragment
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseFragment<VM : BaseAndroidViewModel, B : ViewDataBinding> :
    DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var binder: B

    @Suppress("UNCHECKED_CAST")
    val viewModel by lazyThreadSafetyNone {
        val persistentViewModelClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<VM>
        return@lazyThreadSafetyNone ViewModelProvider(this, viewModelFactory)
            .get(persistentViewModelClass)
    }

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun initialize()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = DataBindingUtil.inflate(inflater, layoutId, container, false)

        with(binder) {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
        }

        initialize()

        return binder.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        startObservers()
    }

    private fun startObservers() {
        observeNavigation()
        observeFailure()
    }

    private fun observeNavigation() {
        viewModel.navigation.observeNonNull(this.viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { command ->
                handleNavigation(command)
            }
        }
    }

    private fun handleNavigation(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.ToDirection -> {
                this.findNavController().navigate(command.directions, getExtras())
            }
            is NavigationCommand.Popup -> {
                with(command) {
                    context?.showPopup(model, listener)
                }
            }
            is NavigationCommand.Back -> this.findNavController().navigateUp()
        }
    }

    private fun observeFailure() {
        viewModel.failurePopup.observeNonNull(this.viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { popupUiModel ->
                context?.showPopup(popupUiModel)
            }
        }
    }

    private fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()
}
