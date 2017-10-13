package com.example.beretta.owoxgallary.arch.view

import android.content.Context
import android.support.annotation.StringRes
import android.view.View


interface ArchViewContract {

    fun getRootView(): View

    fun getActivityContext(): Context

    fun showError(error: String?)

    fun showError(@StringRes stringResId: Int)

    fun showMessage(@StringRes srtResId: Int)

    fun showMessage(message: String)
}

