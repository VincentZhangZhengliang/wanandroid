package com.python.wanandroid.ui.setting

import com.python.wanandroid.R
import com.python.wanandroid.base.BaseActivity
import com.python.wanandroid.ui.setting.event.SignOutEvent
import com.python.wanandroid.ui.setting.presenter.SettingPresenter
import com.python.wanandroid.ui.setting.view.ISettingView
import com.python.wanandroid.utils.Constant
import com.python.wanandroid.utils.Preference
import kotlinx.android.synthetic.main.activity_setting.*
import org.greenrobot.eventbus.EventBus

class SettingActivity : BaseActivity(), ISettingView {

    val presenter = SettingPresenter(this@SettingActivity)
    var isLogin : Boolean by Preference(Constant.LOGIN, false)
    var name : String by Preference(Constant.USERNAME, "")
    var psw : String by Preference(Constant.PASSWORD, "")
    var spDomain : String by Preference(Constant.DOMAIN, "")

    override fun getLayoutId() : Int {
        return R.layout.activity_setting
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar.statusBarView(view4).init()
    }

    override fun initView() {
        super.initView()
        activity_setting_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        activity_setting_toolbar.title = getString(R.string.setting)
    }

    override fun initData() {
        super.initData()
        activity_setting_btn_signout.isEnabled = isLogin
    }

    override fun initListener() {
        super.initListener()
        activity_setting_toolbar.setNavigationOnClickListener { finish() }
        activity_setting_btn_signout.setOnClickListener {
            presenter.signOut()
        }
    }

    override fun signOutSuccess() {
        EventBus.getDefault().post(SignOutEvent(true))
        finish()
    }

}
