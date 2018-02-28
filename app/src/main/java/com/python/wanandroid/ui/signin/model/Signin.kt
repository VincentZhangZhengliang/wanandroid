package com.python.wanandroid.ui.signin.model

/**
 * Created by Vincent;
 * Created on 2018/2/28;
 * DSC:
 */

//{"data":{"collectIds":[],"email":"529889220@qq.com","icon":"","id":2933,"password":"zhang343568","type":0,"username":"VincentZhang"},"errorCode":0,"errorMsg":""}
data class SignInBean(var data: SignInDataBean, var errorCode: Int, var errorMsg: String)

data class SignInDataBean(var collectIds: List<Int>, var email: String, var icon: String, var id: Long, var password: String, var type: Int, var username: String)
