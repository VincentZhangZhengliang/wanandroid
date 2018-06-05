package com.python.wanandroid.ui.signup

import android.text.TextUtils
import android.view.TextureView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.python.wanandroid.R
import com.python.wanandroid.base.BaseActivity
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function3
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_sign_up
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar.statusBarView(activity_sign_up_v).init()
    }

    override fun initListener() {
        super.initListener()

        val nameObservable = RxTextView.textChanges(activity_sign_up_tiet_name).skip(1)
        val pswObservable = RxTextView.textChanges(activity_sign_up_tiet_psw).skip(1)
        val repswObservable = RxTextView.textChanges(activity_sign_up_tiet_confirm).skip(1)

        Observable.combineLatest(nameObservable, pswObservable, repswObservable, object : Function3<CharSequence, CharSequence, CharSequence, Boolean> {
            override fun apply(t1: CharSequence, t2: CharSequence, t3: CharSequence): Boolean {
                val nameB = !TextUtils.isEmpty(activity_sign_up_tiet_name.text)
                val pswB = !TextUtils.isEmpty(activity_sign_up_tiet_psw.text)
                val repswB = !TextUtils.isEmpty(activity_sign_up_tiet_confirm.text)
                return nameB && pswB && repswB
            }
        }).subscribe {
            activity_sign_up_btn_signin.isEnabled = it
        }
    }

}
