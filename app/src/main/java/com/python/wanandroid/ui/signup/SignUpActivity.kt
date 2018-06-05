package com.python.wanandroid.ui.signup

import android.text.TextUtils
import android.view.TextureView
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import com.python.wanandroid.R
import com.python.wanandroid.base.BaseActivity
import com.python.wanandroid.ui.signup.event.RegisterEvent
import com.python.wanandroid.ui.signup.presenter.SignupPresenter
import com.python.wanandroid.ui.signup.view.ISignupView
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function3
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.greenrobot.eventbus.EventBus

class SignUpActivity : BaseActivity(), ISignupView {

    val presenter = SignupPresenter(this)

    override fun getLayoutId() : Int {
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
        Observable.combineLatest(nameObservable, pswObservable, repswObservable, Function3<CharSequence, CharSequence, CharSequence, Boolean> { t1, t2, t3 ->
            val nameB = ! TextUtils.isEmpty(activity_sign_up_tiet_name.text)
            val pswB = ! TextUtils.isEmpty(activity_sign_up_tiet_psw.text)
            val repswB = ! TextUtils.isEmpty(activity_sign_up_tiet_confirm.text)
            nameB && pswB && repswB
        }).subscribe {
            activity_sign_up_btn_signin.isEnabled = it
        }

        activity_sign_up_btn_signin.setOnClickListener {
            val name = activity_sign_up_tiet_name.text.toString()
            val password = activity_sign_up_tiet_psw.text.toString()
            val repassword = activity_sign_up_tiet_confirm.text.toString()
            presenter.register(name, password, repassword)
        }
    }

    /**
     * 注册成功
     */
    override fun signUpSuccess() {

    }

    /**
     * 注册失败
     */
    override fun signUpFail() {
        toast("注册失败")
    }

    /**
     * 注册成功，登陆失败
     * finish signup  转去登陆页
     */
    override fun signInFail() {
        toast("注册成功,请登陆")
        finish()
    }

    /**
     * 注册成功
     * finish signup signin
     */
    override fun signInSuccess() {
        //finish signup signin
        EventBus.getDefault().post(RegisterEvent(true))
        finish()
    }

    override fun toast(msg : String) {
        Toast.makeText(this@SignUpActivity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun loading() {

    }

    override fun hideLoading() {

    }

}
