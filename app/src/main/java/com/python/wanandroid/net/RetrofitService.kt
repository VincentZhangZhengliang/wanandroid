package com.python.wanandroid.net

import com.python.wanandroid.ui.home.model.ArticleBean
import com.python.wanandroid.ui.home.model.BannerBean
import com.python.wanandroid.ui.home.model.CollectBean
import com.python.wanandroid.ui.signin.model.SignInBean
import com.python.wanandroid.ui.system.model.TreeBean
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by Python;
 * Created on 2018/2/25;
 * DSC:
 */
interface RetrofitService {
    /**
     *  首页文章列表
     */
    @GET("article/list/{page}/json")
    fun getArticleList(@Path("page") page: Int): Observable<ArticleBean>

    /**
     * 首页banner
     */
    @GET("banner/json")
    fun getBanner(): Observable<BannerBean>

    /**
     * 常用网站
     */
    @GET("friend/json")
    fun friend(): Observable<String>

    /**
     * 搜索热词
     */
    @GET("hotkey/json")
    fun hotkey(): Observable<String>

    /**
     * 体系数据
     */
    @GET("tree/json")
    fun getTree(): Observable<TreeBean>

    /**
     * 知识体系下的文章
     * article/list/0?cid=60    0:分页   cid 分类id
     */
    @GET("article/list/{page}")
    fun treeArticle(@Path("page") id: Int, @Query("cid") cid: Long): Observable<String>

    /**
     * 导航数据
     */
    @GET("navi/json")
    fun navi(): Observable<String>

    /**
     * 项目分类
     */
    @GET("project/tree/json")
    fun projectTree(): Observable<String>

    /**
     * 项目列表数据
     *  project/list/1/json?cid=294  1 分页  cid 分类id
     */
    @GET("project/list/{page}/json")
    fun projectList(@Path("page") page: Int, @Query("cid") cid: Long): Observable<String>

    /**
     * 登录
     */
    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<SignInBean>


    /**
     * 注册
     */
    @POST("user/register")
    @FormUrlEncoded
    fun register(@Field("username") username: String, @Field("password") password: String, @Field("repassword") repassword: String): Observable<String>

    /**
     * 收藏文章列表
     * lg/collect/list/0/json  0  页码
     */
    @GET("lg/collect/list/{page}/json")
    fun collectList(@Path("page") page: Int): Observable<String>

    /**
     * 收藏站内文章
     *  lg/collect/1165/json   1165  id
     */
    @POST("lg/collect/{id}/json")
    fun collect(@Path("id") id: Long): Observable<CollectBean>

    /**
     * 收藏站外文章
     */
    @POST("lg/collect/add/json")
    @FormUrlEncoded
    fun collectAdd(@Field("title") title: String, @Field("author") author: String, @Field("link") link: String): Observable<String>

    /**
     * 取消收藏
     */
    @POST("lg/uncollect/{id}/json")
    fun uncollect(@Path("id") id: Long): Observable<CollectBean>

    /**
     * 取消收藏
     */
    @POST("lg/uncollect_originId/{id}/json")
    fun uncollect2(@Path("id") id: Long): Observable<CollectBean>

    /**
     * 收藏网站列表
     */
    @GET("lg/collect/usertools/json")
    fun collectUserTools(): Observable<String>

    /**
     * 收藏网址
     */
    @POST("lg/collect/addtool/json")
    @FormUrlEncoded
    fun collectAddTool(@Field("name") name: String, @Field("link") link: String): Observable<String>

    /**
     * 编辑收藏网站
     */
    @POST("lg/collect/updatetool/json")
    @FormUrlEncoded
    fun collectUpdateTool(@Field("name") name: String, @Field("link") link: String, @Field("id") id: Long): Observable<String>

    /**
     *  删除收藏网站
     */
    @POST("lg/collect/deletetool/json")
    @FormUrlEncoded
    fun collectDeleteTool(@Field("id") id: Long): Observable<String>

    /**
     * 搜索
     */
    @POST("article/query/{page}/json")
    @FormUrlEncoded
    fun articleQuery(@Path("page") page: Int, @Field("k") k: String): Observable<String>
}