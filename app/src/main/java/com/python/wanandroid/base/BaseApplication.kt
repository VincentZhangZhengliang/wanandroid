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
import com.python.wanandroid.utils.LogUtil
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.IUmengRegisterCallback
import com.umeng.message.PushAgent
import com.umeng.message.UmengAdHandler


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
        createNotificationChannel()
        initJPush()
        initUmeng()
    }

    private fun initJPush() {
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }

    /**
     * 初始化Umeng
     */
    private fun initUmeng() {
        UMConfigure.init(this, "5b4c47938f4a9d2b950000cb", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "7201a724795f8897a004b22f7dfe1f91")
        UMConfigure.setLogEnabled(true)
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL)
        MobclickAgent.openActivityDurationTrack(false)
        PushAgent.getInstance(this).register(object : IUmengRegisterCallback {
            override fun onSuccess(deviceToken: String?) {
                LogUtil.e("deviceToken = $deviceToken")
            }

            override fun onFailure(p0: String?, p1: String?) {

            }
        })

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