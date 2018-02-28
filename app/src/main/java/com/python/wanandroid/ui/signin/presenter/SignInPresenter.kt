package com.python.wanandroid.ui.signin.presenter

import com.python.wanandroid.ui.signin.biz.SignInBiz
import com.python.wanandroid.ui.signin.view.ISignInView
import com.python.wanandroid.utils.Constant
import com.python.wanandroid.utils.Preference
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by Vincent;
 * Created on 2018/2/28;
 * DSC:
 */
class SignInPresenter(var iView: ISignInView) {


    var isLogin: Boolean by Preference(Constant.LOGIN, false)
    var name: String by Preference(Constant.USERNAME, "")
    var psw: String by Preference(Constant.PASSWORD, "")


    val biz = SignInBiz()

    fun signIn(username: String, password: String) {
        Timber.e("username  = $username , password = $password")
        biz.signIn(username, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.errorCode == 0) {
                        iView.toast("登录成功")
                        isLogin = true
                        name = it.data.username
                        psw = it.data.password
                    } else {
                        iView.toast(it.errorMsg)
                    }
                }
    }

}