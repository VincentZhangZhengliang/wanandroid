package com.python.wanandroid.ui.collect.view

import com.python.wanandroid.ui.collect.model.CollectionListBean

/**
 * @author Python
 * @date 2018/3/4
 * @desc
 */
interface ICollectionView {

    fun toast(msg: String)

    fun setView(datas: List<CollectionListBean>)

}