package com.python.wanandroid.ui.signin

import android.content.Intent
import android.widget.Toast
import com.python.wanandroid.R
import com.python.wanandroid.base.BaseActivity
import com.python.wanandroid.ui.signin.presenter.SignInPresenter
import com.python.wanandroid.ui.signin.view.ISignInView
import com.python.wanandroid.ui.signup.SignUpActivity
import com.python.wanandroid.ui.signup.event.RegisterEvent
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * 登录
 */
class SignInActivity : BaseActivity(), ISignInView {

    override fun toast(msg : String) {
        Toast.makeText(this@SignInActivity, msg, Toast.LENGTH_SHORT).show()
    }

    val presenter = SignInPresenter(this)

    override fun getLayoutId() : Int {
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

    private fun signout() {
        presenter.signout()
    }

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

    @Subscribe
    fun onRegisterEvent(registerEvent : RegisterEvent) {
        val success = registerEvent.success
        if (success) finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this@SignInActivity)
    }

}
