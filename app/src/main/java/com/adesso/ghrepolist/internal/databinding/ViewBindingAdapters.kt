package com.adesso.ghrepolist.internal.databinding

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.adesso.ghrepolist.R
import com.adesso.ghrepolist.base.BaseListAdapter
import com.adesso.ghrepolist.base.ListAdapterItem
import com.adesso.ghrepolist.internal.extension.loadImage

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(view: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = view.adapter as BaseListAdapter<ViewDataBinding, ListAdapterItem>?
    adapter?.submitList(list)
}

@BindingAdapter("imageFromUrl", "placeholderRes", "errorRes", requireAll = false)
fun setImage(
    view: ImageView,
    url: String?,
    @DrawableRes placeholderRes: Int?,
    @DrawableRes errorRes: Int?
) {
    val defaultDrawable = R.drawable.ic_launcher_background

    view.loadImage(
        url,
        placeholderRes ?: defaultDrawable,
        errorRes ?: defaultDrawable
    )
}