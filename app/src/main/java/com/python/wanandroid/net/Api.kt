package com.python.wanandroid.net

import com.python.wanandroid.ui.home.model.ArticleBean
import com.python.wanandroid.ui.home.model.BannerBean
import com.python.wanandroid.ui.home.model.CollectBean
import com.python.wanandroid.ui.signin.model.SignInBean
import com.python.wanandroid.ui.system.model.TreeBean
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

    fun getBanner(): Observable<BannerBean> {
        return RetrofitManager.getInstance().getBanner()
    }

    fun friend(): Observable<String> {
        return RetrofitManager.getInstance().friend()
    }

    fun hotkey(): Observable<String> {
        return RetrofitManager.getInstance().hotkey()
    }

    fun getTree(): Observable<TreeBean> {
        return RetrofitManager.getInstance().getTree()
    }

    fun treeArticle(): Observable<String> {
        return RetrofitManager.getInstance().treeArticle(0, 0)
    }

    fun navi(): Observable<String> {
        return RetrofitManager.getInstance().navi()
    }

    fun projectTree(): Observable<String> {
        return RetrofitManager.getInstance().projectTree()
    }

    fun projectList(): Observable<String> {
        return RetrofitManager.getInstance().projectList(0, 0)
    }

    fun login(username: String, password: String): Observable<SignInBean> {
        return RetrofitManager.getInstance().login(username, password)
    }

    fun register(username: String, password: String): Observable<String> {
        return RetrofitManager.getInstance().register(username, password, "")
    }

    fun collectList(username: String, password: String): Observable<String> {
        return RetrofitManager.getInstance().collectList(0)
    }

    fun collect(id: Long): Observable<CollectBean> {
        return RetrofitManager.getInstance().collect(id)
    }

    fun collectAdd(username: String, password: String): Observable<String> {
        return RetrofitManager.getInstance().collectAdd("", "", "")
    }

    fun uncollect(id: Long): Observable<CollectBean> {
        return RetrofitManager.getInstance().uncollect(0)
    }

    fun uncollect2(id: Long): Observable<CollectBean> {
        return RetrofitManager.getInstance().uncollect2(id)
    }

    fun collectUserTools(username: String, password: String): Observable<String> {
        return RetrofitManager.getInstance().collectUserTools()
    }

    fun collectAddTool(username: String, password: String): Observable<String> {
        return RetrofitManager.getInstance().collectAddTool("", "")
    }

    fun collectUpdateTool(username: String, password: String): Observable<String> {
        return RetrofitManager.getInstance().collectUpdateTool("", "", 0)
    }

    fun collectDeleteTool(username: String, password: String): Observable<String> {
        return RetrofitManager.getInstance().collectDeleteTool(0)
    }

    fun articleQuery(username: String, password: String): Observable<String> {
        return RetrofitManager.getInstance().articleQuery(0, "")
    }

}