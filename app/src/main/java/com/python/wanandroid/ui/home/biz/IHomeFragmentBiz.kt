package com.python.wanandroid.ui.home.biz

import com.python.wanandroid.ui.home.model.ArticleBean
import com.python.wanandroid.ui.home.model.BannerBean
import io.reactivex.Observable

/**
 * Created by Vincent;
 * Created on 2018/2/26;
 * DSC:
 */
interface IHomeFragmentBiz {

    fun getArticleList(page: Int): Observable<ArticleBean>

    fun getBanner(): Observable<BannerBean>

}