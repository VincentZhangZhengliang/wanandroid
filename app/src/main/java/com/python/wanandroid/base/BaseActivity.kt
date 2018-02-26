package com.python.wanandroid.base

import android.os.Bundle
import com.gyf.barlibrary.ImmersionBar
import com.zhy.autolayout.AutoLayoutActivity

abstract class BaseActivity : AutoLayoutActivity() {

    lateinit var mImmersionBar: ImmersionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initImmersionBar()
        initListener()
        initData()
    }

    abstract fun getLayoutId(): Int

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
