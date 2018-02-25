package com.python.wanandroid.net

import android.content.Context
import com.google.gson.GsonBuilder
import com.python.wanandroid.utils.HttpUrls
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Python on 2018/2/25.
 */
object RetrofitManager {

    fun getInstance(baseUrl: String = HttpUrls.BASE_URl): RetrofitService {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create()
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(OkHttpManager.getClient()).build()
        return retrofit.create(RetrofitService::class.java)
    }

}