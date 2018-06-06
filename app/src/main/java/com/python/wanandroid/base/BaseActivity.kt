package com.python.wanandroid.base

import android.os.Bundle
import com.gyf.barlibrary.ImmersionBar
import me.imid.swipebacklayout.lib.SwipeBackLayout
import me.imid.swipebacklayout.lib.app.SwipeBackActivity

abstract class BaseActivity : SwipeBackActivity() {

    lateinit var mImmersionBar : ImmersionBar
    lateinit var mSwipeBackLayout : SwipeBackLayout

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mSwipeBackLayout = swipeBackLayout
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT)
        initData()
        initView()
        initImmersionBar()
        initListener()
    }

    abstract fun getLayoutId() : Int

    protected open fun initView() {}

    protected open fun initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar.init()  //所有子类都将继承这些相同的属性
    }

    protected open fun initListener() {}

    protected open fun initData() {}

    override fun onDestroy() {
        super.onDestroy()
        mImmersionBar.destroy()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }


}
