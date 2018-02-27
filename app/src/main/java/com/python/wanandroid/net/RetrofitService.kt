package com.python.wanandroid.net

import com.python.wanandroid.ui.home.model.ArticleBean
import com.python.wanandroid.ui.home.model.BannerBean
import com.python.wanandroid.ui.system.model.TreeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Python;
 * Created on 2018/2/25;
 * DSC:
 */
interface RetrofitService {

    @GET("article/list/{page}/json")
    fun getArticleList(@Path("page") page: Int): Observable<ArticleBean>

    @GET("banner/json")
    fun getBanner(): Observable<BannerBean>

    @GET("tree/json")
    fun getTree(): Observable<TreeBean>

    @POST("user/login")
    fun login(username: String, password: String): Observable<String>

}