package com.example.beretta.owoxgallary.arch.view

import androidx.annotation.StringRes
import android.view.View


interface ArchViewContract {

    fun getRootView(): View

    fun showError(error: String?)

    fun showError(@StringRes stringResId: Int)

    fun showMessage(@StringRes srtResId: Int)

    fun showMessage(message: String)
}

