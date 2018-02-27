package com.python.wanandroid.ui.system.view

import com.python.wanandroid.ui.system.model.TreeDataBean

/**
 * Created by Vincent;
 * Created on 2018/2/27;
 * DSC:
 */
interface ISystemView {

    fun toast(msg: String)

    fun setView(data: List<TreeDataBean>)

}