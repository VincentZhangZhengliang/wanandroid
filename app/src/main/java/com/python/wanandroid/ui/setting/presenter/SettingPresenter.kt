package com.python.wanandroid.ui.setting.presenter

import com.python.wanandroid.ui.setting.view.ISettingView
import com.python.wanandroid.utils.Constant
import com.python.wanandroid.utils.Preference

/**
 * @author Python
 * @date 2018/6/6
 * @desc
 */
class SettingPresenter(var iView : ISettingView) {

    var isLogin : Boolean by Preference(Constant.LOGIN, false)
    var name : String by Preference(Constant.USERNAME, "")
    var psw : String by Preference(Constant.PASSWORD, "")
    var spDomain : String by Preference(Constant.DOMAIN, "")

    fun signOut() {
        isLogin = false
        name = ""
        psw = ""
        spDomain = ""
        iView.signOutSuccess()
    }


}