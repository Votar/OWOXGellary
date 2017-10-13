package com.example.beretta.owoxgallary.arch.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.example.beretta.owoxgallary.arch.viewmodel.ArchViewModelContract

abstract class BaseArchFragment<in V : ArchViewContract, out T : ArchViewModelContract<V>> :
        Fragment(),
        ArchViewContract {

    protected abstract fun getViewModel(): T

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()
        getViewModel().attachView(this as V)
    }

    override fun onStop() {
        super.onStop()
        getViewModel().detachView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun getActivityContext(): Context = activity

    override fun showError(error: String?) {
        view?.showMessage(error)
    }

    override fun showError(stringResId: Int) {
        view?.showMessage(getString(stringResId))
    }

    override fun showMessage(srtResId: Int) {
        view?.showMessage(getString(srtResId))
    }

    override fun showMessage(message: String) {
        view?.showMessage(message)
    }
}