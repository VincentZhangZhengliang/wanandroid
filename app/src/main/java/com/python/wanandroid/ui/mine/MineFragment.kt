package com.python.wanandroid.ui.mine


import android.support.v4.app.Fragment
import com.gyf.barlibrary.ImmersionBar
import com.python.wanandroid.R
import com.python.wanandroid.base.LazyLoadBaseFragment
import kotlinx.android.synthetic.main.fragment_mine.*


/**
 * A simple [Fragment] subclass.
 */
class MineFragment : LazyLoadBaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView() {
        ImmersionBar.with(this).statusBarView(fragment_mine_v).init()

    }

    override fun initImmersionBar() {
        super.initImmersionBar()
    }

    override fun initListener() {
    }

    override fun initData() {
    }

}
