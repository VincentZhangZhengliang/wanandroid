package com.python.wanandroid.ui.setting

import com.python.wanandroid.R
import com.python.wanandroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity() {

    override fun getLayoutId(): Int {
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
    }

    override fun initListener() {
        super.initListener()
        activity_setting_toolbar.setNavigationOnClickListener { finish() }
    }


}
