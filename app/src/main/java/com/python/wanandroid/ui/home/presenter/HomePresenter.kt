package com.python.wanandroid.ui.home.presenter

import com.python.wanandroid.ui.home.biz.HomeFragmentBiz
import com.python.wanandroid.ui.home.model.RefreshType
import com.python.wanandroid.ui.home.view.IHomeView
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Vincent;
 * Created on 2018/2/26;
 * DSC:
 */
class HomePresenter(var iView: IHomeView) {

    val biz = HomeFragmentBiz()

    fun getArticleList(page: Int, type: RefreshType) {
        biz.getArticleList(page).observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it.errorCode == 0) {
                iView.refreshView(it.data,type)
            } else {
                iView.toast(it.errorMsg)
            }
        }
    }

    fun getBanner() {
        biz.getBanner().observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.errorCode == 0) {
                        iView.setBanner(it.data)
                    } else {
                        iView.toast(it.errorMsg)
                    }
                }
    }

}