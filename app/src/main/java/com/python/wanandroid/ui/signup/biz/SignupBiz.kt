package com.python.wanandroid.ui.signup.biz

import com.python.wanandroid.net.Api
import com.python.wanandroid.ui.signin.model.SignInBean
import com.python.wanandroid.ui.signup.model.SignUpBean
import io.reactivex.Observable
import io.reactivex.Observer

/**
 * @author Python
 * @date 2018/6/5
 * @desc
 */
class SignupBiz : ISignupBiz {

    override fun signIn(username : String, password : String) : Observable<SignInBean> {
        return Api.login(username, password)
    }

    override fun register(username : String, password : String, repassword : String) : Observable<SignUpBean> {
        return Api.register(username, password, repassword)
    }

}