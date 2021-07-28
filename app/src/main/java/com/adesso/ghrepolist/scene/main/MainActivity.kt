package com.adesso.ghrepolist.scene.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.adesso.ghrepolist.R
import com.adesso.ghrepolist.base.BaseBindingActivity
import com.adesso.ghrepolist.databinding.ActivityMainBinding
import com.adesso.ghrepolist.internal.extension.observeNonNull
import com.adesso.ghrepolist.internal.extension.showPopup
import com.adesso.ghrepolist.navigation.NavigationCommand

class MainActivity : BaseBindingActivity<MainViewModel, ActivityMainBinding>() {

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override val layoutId get() = R.layout.activity_main

    val navController: NavController by lazy { findNavController(R.id.main_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binder.viewModel = viewModel
        binder.lifecycleOwner = this

        observeNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    private fun observeNavigation() {
        viewModel.navigation.observeNonNull(this) {
            it.getContentIfNotHandled()?.let { command ->
                handleNavigation(command)
            }
        }
    }

    private fun handleNavigation(command: NavigationCommand) {
        when (command) {
            is NavigationCommand.ToDirection -> navController.navigate(command.directions)
            is NavigationCommand.Popup -> showPopup(command.model, command.listener)
            is NavigationCommand.Back -> navController.navigateUp()
        }
    }
}
