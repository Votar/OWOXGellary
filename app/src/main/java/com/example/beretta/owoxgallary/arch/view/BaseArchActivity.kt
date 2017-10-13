package com.example.beretta.owoxgallary.arch.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.beretta.owoxgallary.arch.viewmodel.ArchViewModelContract

/**
 * Realize attach/detach functions for our presentation layer
 */
abstract class BaseArchActivity<in V : ArchViewContract, out T : ArchViewModelContract<V>>
    : AppCompatActivity(), ArchViewContract {
    /**
     * Base ViewModel
     */
    protected abstract fun getViewModel(): T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getViewModel().attachView(this as V)
    }


    override fun getActivityContext(): Context = applicationContext

    override fun showError(error: String?) {
        getRootView().showMessage(error)
    }

    override fun showError(stringResId: Int) {
        getRootView().showMessage(getString(stringResId))
    }

    override fun showMessage(srtResId: Int) {
        getRootView().showMessage(getString(srtResId))
    }

    override fun showMessage(message: String) {
        getRootView().showMessage(message)
    }

    override fun onDestroy() {
        getViewModel().detachView()
        super.onDestroy()
    }

}