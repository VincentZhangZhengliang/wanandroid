package com.python.wanandroid.ui.signup.presenter

import com.python.wanandroid.ui.signin.model.SignInBean
import com.python.wanandroid.ui.signup.biz.SignupBiz
import com.python.wanandroid.ui.signup.view.ISignupView
import com.python.wanandroid.utils.Constant
import com.python.wanandroid.utils.Preference
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @author Python
 * @date 2018/6/5
 * @desc
 */
class SignupPresenter(var iView : ISignupView) {

    private val biz = SignupBiz()
    private var isLogin : Boolean by Preference(Constant.LOGIN, false)
    private var name : String by Preference(Constant.USERNAME, "")
    private var psw : String by Preference(Constant.PASSWORD, "")
    private val signUpFailStr = "sign up fail"
    private val length = signUpFailStr.length

    fun register(username : String, password : String, repassword : String) = biz.register(username, password, repassword).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap { t ->
        if (t.errorCode == 0) {
            name = t.data.username
            psw = t.data.password
            biz.signIn(t.data.username, t.data.password).observeOn(Schedulers.io())
        } else {
            Observable.error(Throwable(signUpFailStr + t.errorMsg))
        }
    }.observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<SignInBean> {

        override fun onComplete() {
        }

        override fun onSubscribe(d : Disposable) {
        }

        override fun onNext(t : SignInBean) {
            if (t.errorCode == 0) {
                isLogin = true
                name = t.data.username
                psw = t.data.password
                iView.signInSuccess()
            } else {
                iView.signInFail()
            }
        }

        override fun onError(e : Throwable) {
            if (e.message !!.contains(signUpFailStr)) {
                //注册失败
                val indexOf = e.message?.indexOf(signUpFailStr)
                if (indexOf != - 1) iView.signUpFail(e.message !!.substring(indexOf !! + length)) else iView.signUpFail("注册失败")
            } else {
                //登陆失败
                iView.signInFail()
            }
        }
    })
}