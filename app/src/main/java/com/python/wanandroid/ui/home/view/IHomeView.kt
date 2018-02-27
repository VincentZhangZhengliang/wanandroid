package com.python.wanandroid.ui.home.view

import com.python.wanandroid.ui.home.model.ArticleDataBean
import com.python.wanandroid.ui.home.model.BannerListBean

/**
 * Created by Vincent;
 * Created on 2018/2/26;
 * DSC:
 */
interface IHomeView {

    fun refreshView(data: ArticleDataBean)

    fun setBanner(data: List<BannerListBean>)

    fun toast(msg: String)

}