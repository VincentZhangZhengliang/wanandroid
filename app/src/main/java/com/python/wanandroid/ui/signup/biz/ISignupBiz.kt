package com.python.wanandroid.ui.signup.biz

import com.python.wanandroid.ui.signin.model.SignInBean
import com.python.wanandroid.ui.signup.model.SignUpBean
import io.reactivex.Observable

/**
 * @author Python
 * @date 2018/6/5
 * @desc
 */
interface ISignupBiz {

    fun register(username : String, password : String, repassword : String) : Observable<SignUpBean>

    fun signIn(username: String, password: String): Observable<SignInBean>
}