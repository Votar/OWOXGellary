package com.example.beretta.owoxgallary.arch.viewmodel

import androidx.lifecycle.ViewModel
import com.example.beretta.owoxgallary.arch.view.ArchViewContract


open class BaseArchViewModel<V : ArchViewContract> : ArchViewModelContract<V>, ViewModel(){

    protected var view: V? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}