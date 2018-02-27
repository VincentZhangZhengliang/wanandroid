package com.python.wanandroid.ui.home.biz

import com.python.wanandroid.net.Api
import com.python.wanandroid.ui.home.model.ArticleBean
import com.python.wanandroid.ui.home.model.BannerBean
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vincent;
 * Created on 2018/2/26;
 * DSC:
 */
class HomeFragmentBiz : IHomeFragmentBiz {

    override fun getBanner(): Observable<BannerBean> {
        return Api.getBanner().subscribeOn(Schedulers.io())
    }

    override fun getArticleList(page: Int): Observable<ArticleBean> {
        return Api.getArticleList(page).subscribeOn(Schedulers.io())
    }

}