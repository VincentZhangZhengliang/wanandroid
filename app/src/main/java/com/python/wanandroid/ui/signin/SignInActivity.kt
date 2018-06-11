package com.python.wanandroid.ui.signin

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import cn.jpush.android.api.JPushInterface
import com.jakewharton.rxbinding2.widget.RxTextView
import com.python.wanandroid.R
import com.python.wanandroid.base.BaseActivity
import com.python.wanandroid.ui.signin.event.SignInEvent
import com.python.wanandroid.ui.signin.presenter.SignInPresenter
import com.python.wanandroid.ui.signin.view.ISignInView
import com.python.wanandroid.ui.signup.SignUpActivity
import com.python.wanandroid.ui.signup.event.SignUpEvent
import com.python.wanandroid.utils.Constant
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * 登录
 */
class SignInActivity : BaseActivity(), ISignInView {

    override fun signInSuccess(username: String) {
        finish()
        setResult(Activity.RESULT_OK)
        EventBus.getDefault().post(SignInEvent(true))
        //TODO:注册极光
        JPushInterface.setAlias(this, Constant.JPUSH_SEQUENCE, username)
    }

    override fun signInFail(msg: String) {
        toast(msg)
    }

    override fun toast(msg: String) {
        Toast.makeText(this@SignInActivity, msg, Toast.LENGTH_SHORT).show()
    }

    val presenter = SignInPresenter(this)

    override fun getLayoutId(): Int {
        return R.layout.activity_sign_in
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar.statusBarView(activity_sign_in_v).init()
    }

    override fun initData() {
        super.initData()
        EventBus.getDefault().register(this@SignInActivity)
    }

    override fun initView() {
        super.initView()
    }

    override fun initListener() {
        super.initListener()

        val nameObservable = RxTextView.textChanges(activity_sign_in_tiet_name).skip(1)
        val pswObservable = RxTextView.textChanges(activity_sign_in_tiet_psw).skip(1)

        Observable.combineLatest(nameObservable, pswObservable, BiFunction<CharSequence, CharSequence, Boolean> { t1, t2 ->
            val nameStr = activity_sign_in_tiet_name.text.toString()
            val pswStr = activity_sign_in_tiet_psw.text.toString()
            !TextUtils.isEmpty(nameStr) && !TextUtils.isEmpty(pswStr)
        }).subscribe {
            activity_sign_in_btn_signin.isEnabled = it
        }

        activity_sign_in_btn_signin.setOnClickListener {
            signin()
        }

        activity_sign_in_iv_qq.setOnClickListener {
            signout()
        }

        activity_sign_in_tv_sign_up.setOnClickListener {
            startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
        }
    }

    /**
     * 退出登录
     */
    private fun signout() {
        presenter.signout()
    }

    /**
     * 登录
     */
    private fun signin() {
        val username = activity_sign_in_tiet_name.text.trim().toString()
        val password = activity_sign_in_tiet_psw.text.trim().toString()
        if (username.isEmpty()) {
            Toast.makeText(this@SignInActivity, "用户名不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.isEmpty()) {
            Toast.makeText(this@SignInActivity, "密码不能为空", Toast.LENGTH_SHORT).show()
            return
        }
        presenter.signIn(username, password)
    }

    /**
     * 注册成功后发送的事件
     */
    @Subscribe
    fun onRegisterEvent(registerEvent: SignUpEvent) {
        val success = registerEvent.success
        if (success) finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this@SignInActivity)
    }

}
