package com.python.wanandroid.ui.mine


import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
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
    }

    override fun initData() {
    }

}
