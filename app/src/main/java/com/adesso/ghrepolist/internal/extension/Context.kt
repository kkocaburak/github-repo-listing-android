package com.adesso.ghrepolist.internal.extension

import android.content.Context
import com.adesso.ghrepolist.internal.popup.Popup
import com.adesso.ghrepolist.internal.popup.PopupListener
import com.adesso.ghrepolist.internal.popup.PopupModel

fun Context.showPopup(model: PopupModel, listener: PopupListener? = null) {
    Popup(this, model, listener).show()
}
