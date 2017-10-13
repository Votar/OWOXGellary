package com.example.beretta.owoxgallary.arch.viewmodel

import android.arch.lifecycle.ViewModel
import com.example.beretta.owoxgallary.arch.view.ArchViewContract


open class BaseArchViewModel<V : ArchViewContract> : ArchViewModelContract<V>, ViewModel(){

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
}