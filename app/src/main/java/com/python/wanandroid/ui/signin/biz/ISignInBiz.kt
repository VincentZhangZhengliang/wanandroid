package com.python.wanandroid.ui.signin.biz

import com.python.wanandroid.ui.signin.model.SignInBean
import io.reactivex.Observable

/**
 * Created by Vincent;
 * Created on 2018/2/28;
 * DSC:
 */
interface ISignInBiz {


    fun signIn(username: String, password: String): Observable<SignInBean>


}