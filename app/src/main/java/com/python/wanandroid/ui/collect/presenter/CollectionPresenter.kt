package com.python.wanandroid.ui.collect.presenter

import android.widget.ImageView
import com.python.wanandroid.ui.collect.biz.CollectionBiz
import com.python.wanandroid.ui.collect.view.ICollectionView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * @author Python
 * @date 2018/3/4
 * @desc
 */
class CollectionPresenter(var iView: ICollectionView) {

    val biz = CollectionBiz()

    fun collectList(username: String, password: String) {
        biz.collectList(username, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.errorCode == 0) {

                        iView.setView(it.data.datas)
                    } else {
                        iView.toast(it.errorMsg)
                    }
                }
    }


}