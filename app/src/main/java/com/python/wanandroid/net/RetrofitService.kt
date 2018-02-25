package com.python.wanandroid.net

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

/**
 * Created by Python on 2018/2/25.
 */
interface RetrofitService {

    @GET("article/list/{page}/json")
    fun getArticleList(@Path("page") page: Int): Observable<List<String>>

}