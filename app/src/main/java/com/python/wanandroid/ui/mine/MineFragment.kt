package com.python.wanandroid.ui.mine


import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.EventLog
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.python.wanandroid.R
import com.python.wanandroid.base.LazyLoadBaseFragment
import com.python.wanandroid.ui.collect.MyCollectionActivity
import com.python.wanandroid.ui.mine.view.IMineView
import com.python.wanandroid.ui.setting.SettingActivity
import com.python.wanandroid.ui.setting.event.SignOutEvent
import com.python.wanandroid.ui.signin.SignInActivity
import com.python.wanandroid.ui.signin.event.SignInEvent
import com.python.wanandroid.utils.Constant
import com.python.wanandroid.utils.LogUtil
import com.python.wanandroid.utils.Preference
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.fragment_mine.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 */
class MineFragment : LazyLoadBaseFragment(), IMineView {

    var isLogin: Boolean by Preference(Constant.LOGIN, false)
    var name: String by Preference(Constant.USERNAME, "")
    var psw: String by Preference(Constant.PASSWORD, "")

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {
        ImmersionBar.with(this).statusBarView(fragment_mine_v).init()
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
    }

    override fun initToolbar() {
        super.initToolbar()
        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.setSupportActionBar(fragment_mine_toolbar)
        fragment_mine_toolbar.apply {
            title = getString(R.string.mine)
            setTitleTextColor(Color.WHITE)
        }

    }

    override fun initListener() {
        fragment_mine_iv_pic.setOnClickListener {
            if (!isLogin) {
                startActivity(Intent(activity, SignInActivity::class.java))
            }
        }
        fragment_mine_rl_setting.setOnClickListener { startActivity(Intent(activity, SettingActivity::class.java)) }
        fragment_mine_rl_my_collection.setOnClickListener {
            if (isLogin) {
                startActivity(Intent(activity, MyCollectionActivity::class.java))
            } else {
                toast("请先登录")
                startActivity(Intent(activity, SignInActivity::class.java))
            }
        }
    }

    override fun initData() {
        if (isLogin) fragment_mine_tv_name.text = name

    }

    override fun toast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Constant.CODE_SIGN_IN -> {
                if (resultCode == Activity.RESULT_OK) {
                    fragment_mine_tv_name.text = name
                }
            }
        }
    }

    @Subscribe
    fun onSignOutEvent(signOutEvent: SignOutEvent) {
        isLogin = false
        fragment_mine_tv_name.text = getString(R.string.tip_login)
    }

    @Subscribe
    fun onSignInEvent(signInEvent: SignInEvent) {
        isLogin = true
        fragment_mine_tv_name.text = name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}
