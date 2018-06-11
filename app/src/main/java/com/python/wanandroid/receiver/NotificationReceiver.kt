package com.python.wanandroid.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber
import java.sql.Time

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Timber.e(intent?.getStringExtra("noti"))
    }

}