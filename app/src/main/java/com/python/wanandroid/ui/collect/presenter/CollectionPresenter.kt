package com.python.wanandroid.ui.collect.presenter

import com.python.wanandroid.ui.collect.biz.CollectionBiz
import com.python.wanandroid.ui.collect.view.ICollectionView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Python
 * @date 2018/3/4
 * @desc
 */
class CollectionPresenter(var iView: ICollectionView) {

    val biz = CollectionBiz()

    fun collectList(p: Int, username: String, password: String) {
        biz.collectList(p, username, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.errorCode == 0) {
                        iView.setView(it.data.datas, it.data.curPage, it.data.pageCount)
                    } else {
                        iView.toast(it.errorMsg)
                    }
                    iView.refreshFinish()
                }
    }

}