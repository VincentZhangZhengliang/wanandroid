package com.python.wanandroid.ui.signup.view

/**
 * @author Python
 * @date 2018/6/5
 * @desc
 */
interface ISignupView {

    fun toast(msg : String)

    fun loading()

    fun hideLoading()

    fun signUpSuccess()

    fun signUpFail(msg:String)

    fun signInFail()

    fun signInSuccess()

}