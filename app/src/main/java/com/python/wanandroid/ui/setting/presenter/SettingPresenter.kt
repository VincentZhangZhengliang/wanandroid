package com.python.wanandroid.ui.setting.presenter

import android.content.Context
import com.python.wanandroid.ui.setting.view.ISettingView
import com.python.wanandroid.utils.Constant
import com.python.wanandroid.utils.Preference
import com.umeng.message.IUmengCallback
import com.umeng.message.PushAgent

/**
 * @author Python
 * @date 2018/6/6
 * @desc
 */
class SettingPresenter(var iView: ISettingView) {

    var isLogin: Boolean by Preference(Constant.LOGIN, false)
    var name: String by Preference(Constant.USERNAME, "")
    var psw: String by Preference(Constant.PASSWORD, "")
    var spDomain: String by Preference(Constant.DOMAIN, "")

    fun signOut() {
        iView.signOutSuccess(name)
        isLogin = false
        name = ""
        psw = ""
        spDomain = ""
    }

    /**
     * 打开/关闭消息推送
     */
    fun openMessagePush(context: Context, isChecked: Boolean) {

        val callback = object : IUmengCallback {

            override fun onSuccess() {
                if (isChecked) {
                    iView.toast("消息推送已打开")
                } else {
                    iView.toast("消息推送已关闭")
                }
            }

            override fun onFailure(p0: String?, p1: String?) {
                iView.toast("p0 = $p0 , p1 = $p1")
            }
        }

        if (isChecked) {
            PushAgent.getInstance(context).enable(callback)
        } else {
            PushAgent.getInstance(context).disable(callback)
        }

    }

    /**
     * 消息免打扰
     */
    fun messageNoDisturb(context: Context, startHour: Int = 0,
                         startMinute: Int = 0, endHour: Int = 0, endMinute: Int = 0) {
        PushAgent.getInstance(context).setNoDisturbMode(startHour, startMinute, endHour, endMinute)
    }

}