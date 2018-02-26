package com.python.wanandroid.ui.home.view

import com.python.wanandroid.ui.home.model.ArticleDataBean

/**
 * Created by Vincent;
 * Created on 2018/2/26;
 * DSC:
 */
interface IHomeView {

    fun refreshView(data: ArticleDataBean)

    fun toast(msg: String)

}