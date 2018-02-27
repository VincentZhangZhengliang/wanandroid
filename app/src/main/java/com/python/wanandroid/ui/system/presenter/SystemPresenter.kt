package com.python.wanandroid.ui.system.presenter

import com.python.wanandroid.ui.system.biz.SystemBiz
import com.python.wanandroid.ui.system.view.ISystemView
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Vincent;
 * Created on 2018/2/27;
 * DSC:
 */
class SystemPresenter(var iView: ISystemView) {

    var biz = SystemBiz()

    fun getTree() {
        biz.getTree().observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.errorCode == 0) {
                        iView.setView(it.data)
                    } else {
                        iView.toast(it.errorMsg)
                    }
                }
    }


}