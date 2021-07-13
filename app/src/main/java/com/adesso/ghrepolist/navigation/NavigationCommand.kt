package com.adesso.ghrepolist.navigation

import androidx.navigation.NavDirections
import com.adesso.ghrepolist.internal.popup.PopupListener
import com.adesso.ghrepolist.internal.popup.PopupModel

sealed class NavigationCommand {
    data class ToDirection(val directions: NavDirections) : NavigationCommand()
    data class Popup(val model: PopupModel, val listener: PopupListener? = null) :
        NavigationCommand()

    object Back : NavigationCommand()
}
