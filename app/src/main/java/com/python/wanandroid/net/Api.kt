package com.python.wanandroid.net

import com.python.wanandroid.ui.home.model.ArticleBean
import com.python.wanandroid.ui.home.model.BannerBean
import io.reactivex.Observable

/**
 * @author Python
 * @date 2018/2/25
 * @desc
 */
object Api {

    /**
     * 首页文章列表
     * @param page 页码
     */
    fun getArticleList(page: Int): Observable<ArticleBean> {
        return RetrofitManager.getInstance().getArticleList(page)
    }

    //http://www.wanandroid.com/banner/json
    fun getBanner(): Observable<BannerBean> {
        return RetrofitManager.getInstance().getBanner()
    }


}