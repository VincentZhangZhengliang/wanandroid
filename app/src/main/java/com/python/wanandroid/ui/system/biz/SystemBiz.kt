package com.python.wanandroid.ui.system.biz

import com.python.wanandroid.net.Api
import com.python.wanandroid.ui.system.model.TreeBean
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vincent;
 * Created on 2018/2/27;
 * DSC:
 */
class SystemBiz : ISystemBiz {

    override fun getTree(): Observable<TreeBean> {
        return Api.getTree().subscribeOn(Schedulers.io())
    }

}