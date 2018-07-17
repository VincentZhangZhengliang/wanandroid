package com.python.wanandroid.ui.splash

import com.umeng.message.inapp.InAppMessageManager
import com.umeng.message.inapp.UmengSplashMessageActivity
import timber.log.Timber


class SplashActivity : UmengSplashMessageActivity() {

    override fun onCustomPretreatment(): Boolean {
        val mInAppMessageManager = InAppMessageManager.getInstance(this)
        //设置应用内消息为Debug模式
        mInAppMessageManager.setInAppMsgDebugMode(true)
        //参数为Activity的完整包路径，下面仅是示例代码，请按实际需求填写
        mInAppMessageManager.setMainActivityPath("com.python.wanandroid.MainActivity")
        return super.onCustomPretreatment()
    }

}
