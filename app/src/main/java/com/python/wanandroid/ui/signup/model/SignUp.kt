package com.python.wanandroid.ui.signup.model

/**
 * @author Python
 * @date 2018/6/5
 * @desc
 */

//"data":{"collectIds":[],"email":"","icon":"","id":6342,"password":"zhang343568","type":0,"username":"Jokerz"},"errorCode":0,"errorMsg":""}

data class SignUpBean(var errorCode : Int, var errorMsg : String, var data : SignUpDataBean)

data class SignUpDataBean(var collectIds : List<Int>, var email : String, var icon : String, var id : String, var password : String, var type : Int, var username : String)
