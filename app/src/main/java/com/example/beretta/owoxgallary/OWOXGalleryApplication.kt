package com.example.beretta.owoxgallary

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.widget.Toast

import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.example.beretta.owoxgallary.network.ApiUnsplash
import com.example.beretta.owoxgallary.network.service.DownloadService

class OWOXGalleryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //Create singleton
        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH)
        registerDownloadActionListener()
    }

    private fun registerDownloadActionListener() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(DownloadService.ACTION_DOWNLOAD_BEGAN)
        intentFilter.addAction(DownloadService.ACTION_DOWNLOAD_FINISHED)

        LocalBroadcastManager.getInstance(this).registerReceiver(actionListener, intentFilter)
    }

    private val actionListener = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, intent: Intent) {
            when (intent.action) {
                DownloadService.ACTION_DOWNLOAD_BEGAN -> {
                    val fileName = intent.getStringExtra(DownloadService.KEY_FILE_NAME)
                    Toast.makeText(applicationContext, getString(R.string.text_download_have_started, fileName), Toast.LENGTH_SHORT).show()
                }
                DownloadService.ACTION_DOWNLOAD_FINISHED -> {
                    val fileName = intent.getStringExtra(DownloadService.KEY_FILE_NAME)
                    val result = intent.getBooleanExtra(DownloadService.KEY_FLAG, false)
                    if (result)
                        Toast.makeText(applicationContext, getString(R.string.text_download_finished_with_success, fileName), Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(applicationContext, getString(R.string.text_download_finished_with_fail, fileName), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
