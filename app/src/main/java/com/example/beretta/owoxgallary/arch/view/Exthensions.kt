package com.example.beretta.owoxgallary.arch.view

import android.view.View
import android.widget.Toast
import com.example.beretta.owoxgallary.R

fun View.showMessage(message: String?) {
    val text: String
    if (message.isNullOrEmpty()) {
        text = this.context.getString(R.string.er_default_inner_error)
    } else
        text = message!!

    Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()

}

