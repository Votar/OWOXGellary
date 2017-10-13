package com.example.beretta.owoxgallary.utils

import android.os.Environment

/**
 * Created by beretta on 13.10.2017.
 */
fun isExternalStorageWritable(): Boolean {
    val state = Environment.getExternalStorageState()
    if (Environment.MEDIA_MOUNTED == state) {
        return true
    }
    return false
}