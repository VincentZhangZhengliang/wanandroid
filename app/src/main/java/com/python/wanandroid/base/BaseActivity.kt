package com.python.wanandroid.base

import android.os.Bundle
import anet.channel.util.Utils.context
import com.gyf.barlibrary.ImmersionBar
import com.umeng.analytics.MobclickAgent
import com.umeng.message.PushAgent
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.app.SwipeBackActivity

abstract class BaseActivity : SwipeBackActivity() {

    lateinit var mImmersionBar: ImmersionBar
    lateinit var mSwipeBackLayout: SwipeBackLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mSwipeBackLayout = swipeBackLayout
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT)
        initData()
        initView()
        initImmersionBar()
        initListener()
    }

    abstract fun getLayoutId(): Int

    protected open fun initView() {}

    protected open fun initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar.init()  //所有子类都将继承这些相同的属性
    }

    protected open fun initListener() {}

    protected open fun initData() {
        PushAgent.getInstance(this).onAppStart();
    }

    override fun onDestroy() {
        super.onDestroy()
        mImmersionBar.destroy()
    }


    override fun onResume() {
        super.onResume()
        MobclickAgent.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPause(this)
    }

    override fun onStop() {
        super.onStop()
    }


}
