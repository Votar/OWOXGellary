package com.example.beretta.owoxgallary.arch.viewmodel

import com.example.beretta.owoxgallary.arch.view.ArchViewContract


interface ArchViewModelContract<in V : ArchViewContract> {
    fun attachView(view: V)
    fun detachView()
}