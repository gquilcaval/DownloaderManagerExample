package com.dms.downloader

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.IntentFilter
import android.database.Cursor
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class MainActivity : AppCompatActivity() {
    private var downloadManager: DownloadManager? = null
    private var progressbar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btn_descargar)
         progressbar = findViewById(R.id.progressBar)
        initializeDownloadManager()

        button.setOnClickListener {
            val d = downloadFile("https://sample-videos.com/zip/100mb.zip")
            Toast.makeText(this, d.toString(), Toast.LENGTH_SHORT).show()
            progressbar?.visibility = View.VISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(DownloadCompletedReceiver(this), IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    /**
     * Initialize download manager
     */
    private fun initializeDownloadManager() {
        downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
    }

    fun downloadFile(url: String): Long {
        val fileName= "TEST2"
        val request = DownloadManager.Request(url.toUri())
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle(fileName)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        return downloadManager!!.enqueue(request)

    }

    fun finishLoading() {
        progressbar?.visibility = View.GONE
    }
}