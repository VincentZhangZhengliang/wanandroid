package com.python.wanandroid.base

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mImmersionBar: ImmersionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
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
