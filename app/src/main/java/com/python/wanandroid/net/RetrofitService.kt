package com.python.wanandroid.net

import com.python.wanandroid.ui.home.model.ArticleBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Python;
 * Created on 2018/2/25;
 * DSC:
 */
interface RetrofitService {

    @GET("article/list/{page}/json")
    fun getArticleList(@Path("page") page: Int): Observable<ArticleBean>

}