package com.python.wanandroid.base

import android.annotation.SuppressLint
import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import cn.jpush.android.api.BasicPushNotificationBuilder
import cn.jpush.android.api.JPushInterface
import com.python.wanandroid.R
import com.python.wanandroid.utils.Preference
import timber.log.Timber
import com.python.wanandroid.MainActivity
import cn.jpush.android.api.CustomPushNotificationBuilder
import com.python.wanandroid.utils.Constant


/**
 * @author Python
 * @date 2018/2/25
 * @desc
 */
class BaseApplication : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: Application? = null

        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Preference.setContext(applicationContext)
        Timber.plant(Timber.DebugTree())
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
        createNotificationChannel()
    }


    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val description = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(Constant.CHANNEL_ID, name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(channel)
        }
    }

}