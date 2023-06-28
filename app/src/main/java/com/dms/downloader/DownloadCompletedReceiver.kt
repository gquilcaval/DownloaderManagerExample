package com.dms.downloader

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import kotlin.math.acos

/**
 * Created by Giancarlos Quilca
 */

class DownloadCompletedReceiver(val activity: MainActivity): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == "android.intent.action.DOWNLOAD_COMPLETE") {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
            if(id != -1L) {

                if (activity!=null) {
                    activity.finishLoading()
                }
                Toast.makeText(context,"DESCARGA FINALIZADA - ${context?.applicationContext?.javaClass?.simpleName}", Toast.LENGTH_SHORT).show()

                println("Download with ID $id finished!")
            }
        }
    }
}