package com.python.wanandroid.utils

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Environment
import android.util.Log

/**
 * 部分机型可能删除了DownloadManager
 */

class DownloadUtil(var context: Context) {

    var manager: DownloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    lateinit var query: DownloadManager.Query

    fun downloadApk(url: String, name: String, path: String): Long {

        //1.创建下载任务
        val request = DownloadManager.Request(Uri.parse(url))
        //移动网络情况下是否允许漫游
        request.setAllowedOverRoaming(false)
        // 指定可以在移动网络下下载
        // request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
        // 可以在wifi下下载
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)

        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setTitle("新版本Apk")
        request.setDescription("Apk Downloading")
        request.setVisibleInDownloadsUi(true)
        request.allowScanningByMediaScanner()
        //设置下载的路径
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name)
        //设置类型为.apk
        request.setMimeType("application/vnd.android.package-archive");

        context.registerReceiver(DownloadReceiver(),
                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

        //2.加入下载队列
        return manager.enqueue(request)
    }

    /**
     * 获取下载信息
     */
    private fun getQuery(downloadId: Long): Int {
        val percentage: Int    //下载进度
        query = DownloadManager.Query()
        val cursor = manager.query(query.setFilterById(downloadId))
        if (cursor != null && cursor.moveToFirst()) {
            val address = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
            val bytesDownloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
            val bytesTotal = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
            val title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE))
            val description = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_DESCRIPTION))
            val downId = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_ID))
            percentage = (bytesDownloaded * 1f / bytesTotal * 100f).toInt()
            cursor.close()
            return percentage
        }
        return 0
    }

}


class DownloadReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        when (action) {
            DownloadManager.ACTION_DOWNLOAD_COMPLETE -> {
                //下载完成 安装
                Log.v("intent", "" + intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0))
                val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0)

                installApk(id)

            }
        }
    }

    private fun installApk(id: Long) {

    }

    fun queryDownloadStatus(context: Context?, id: Long) {
        val manager = context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val query = DownloadManager.Query()
        query.setFilterById(id)
        val c = manager.query(query)
        if (c.moveToFirst()) {
            val status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))
            when (status) {
                DownloadManager.STATUS_PAUSED -> {
                    Log.v("debug", "STATUS_PAUSED")

                }
                DownloadManager.STATUS_PENDING -> {
                    Log.v("debug", "STATUS_PENDING")
                }
                DownloadManager.STATUS_RUNNING -> {
                    Log.v("debug", "下载中")
                }
                DownloadManager.STATUS_SUCCESSFUL -> {
                    //完成
                    Log.v("debug", "下载完成")
                }
                DownloadManager.STATUS_FAILED -> {
                    //清除已下载的内容，重新下载
                    Log.v("debug", "STATUS_FAILED")
                    manager.remove(id)
                }
            }
        }

    }
}