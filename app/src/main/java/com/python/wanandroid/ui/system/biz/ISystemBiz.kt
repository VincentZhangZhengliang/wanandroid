package com.python.wanandroid.ui.system.biz

import com.python.wanandroid.ui.system.model.TreeBean
import io.reactivex.Observable

/**
 * Created by Vincent;
 * Created on 2018/2/27;
 * DSC:
 */
interface ISystemBiz {

    fun getTree(): Observable<TreeBean>

}