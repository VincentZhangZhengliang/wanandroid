package com.python.wanandroid.net

import io.reactivex.Observable

/**
 * @author Python
 * @date 2018/2/25
 * @desc
 */
object Api {

    fun getArticleList(page: Int): Observable<List<String>> {
        return RetrofitManager.getInstance().getArticleList(page)
    }

}