package com.example.beretta.owoxgallary.network.service

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Environment
import android.support.v4.content.LocalBroadcastManager
import com.example.beretta.owoxgallary.R
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL


class DownloadService : IntentService("DownloadService") {
    companion object {
        const val ACTION_DOWNLOAD_FINISHED = "com.example.beretta.owoxgallary.network.service.DownloadService.ACTION_DOWNLOAD"
        const val ACTION_DOWNLOAD_BEGAN = "com.example.beretta.owoxgallary.network.service.DownloadService.ACTION_BEGAN"
        const val KEY_FLAG = "ext_l_result_flag"
        const val KEY_FILE_NAME = "ext_k_file_name"
        const val KEY_URL = "ext_k_url"

        fun getIntent(ctx: Context, url: String): Intent {
            val intent = Intent(ctx, DownloadService::class.java)
            intent.putExtra(KEY_URL, url)
            return intent
        }
    }

    override fun onHandleIntent(intent: Intent) {
        val urlToDownload = intent.getStringExtra(KEY_URL)
        if (urlToDownload.isNullOrEmpty())
            throw IllegalStateException("No url in intent")
        val fileName = urlToDownload.substring(urlToDownload.lastIndexOf('/') + 1, urlToDownload.length)

        try {
            val url = URL(urlToDownload)
            val dir = File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), getString(R.string.app_name))

            if (!dir.exists()) {
                dir.mkdirs()
            }

            val connection = url.openConnection()
            connection.connect()
            // this will be useful so that you can show a typical 0-100% progress bar
//            val fileLength = connection.getContentLength()
            // download the file
            sendDownloadBegan(fileName + ".jpg")
            val file = File(dir, fileName + ".jpg")
            if (file.exists().not())
                file.createNewFile()
            val input = BufferedInputStream(connection.getInputStream())
            val output = FileOutputStream(file)

            val data = ByteArray(1024)
            do {
                val count: Int = input.read(data)
                if (count == -1)
                    break
                output.write(data, 0, count)
            } while (true)

            output.flush()
            output.close()
            input.close()
        } catch (e: IOException) {
            e.printStackTrace()
            sendResult(false, fileName)
            return
        }
        sendResult(true, fileName)
    }

    private fun sendDownloadBegan(fileName: String) {
        val intent = Intent()
        intent.action = ACTION_DOWNLOAD_BEGAN
        intent.putExtra(KEY_FILE_NAME, fileName)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }

    private fun sendResult(success: Boolean, fileName: String) {
        val intent = Intent()
        intent.action = ACTION_DOWNLOAD_FINISHED
        intent.putExtra(KEY_FLAG, success)
        intent.putExtra(KEY_FILE_NAME, fileName)
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
    }


}