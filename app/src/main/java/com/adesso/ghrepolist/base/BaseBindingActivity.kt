package com.adesso.ghrepolist.base

import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.adesso.ghrepolist.internal.util.functional.lazyThreadSafetyNone

abstract class BaseBindingActivity<VM : BaseAndroidViewModel, B : ViewDataBinding> :
    BaseActivity<VM>() {

    protected val binder by lazyThreadSafetyNone<B> {
        DataBindingUtil.setContentView(this, layoutId)
    }

    @get:LayoutRes
    abstract val layoutId: Int
}