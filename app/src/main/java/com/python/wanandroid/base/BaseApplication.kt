package com.python.wanandroid.base

import android.annotation.SuppressLint
import android.app.Application
import cn.jpush.android.api.JPushInterface
import com.python.wanandroid.utils.Preference
import timber.log.Timber

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
        JPushInterface.setAlias(this,123,"18271632711")
    }

}