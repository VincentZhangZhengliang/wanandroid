package com.python.wanandroid.ui.collect.biz

import com.python.wanandroid.ui.collect.model.CollectionBean
import io.reactivex.Observable

/**
 * @author Python
 * @date 2018/3/4
 * @desc
 */
interface ICollectionBiz {

    fun collectList(username: String, password: String): Observable<CollectionBean>

}