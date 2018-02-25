package com.python.wanandroid.base

import android.app.Application
import com.python.wanandroid.utils.Preference
import timber.log.Timber

/**
 * @author Python
 * @date 2018/2/25
 * @desc
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Preference.setContext(applicationContext)
        Timber.plant(Timber.DebugTree())

    }
}