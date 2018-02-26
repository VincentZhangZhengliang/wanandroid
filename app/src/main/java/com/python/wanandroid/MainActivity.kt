package com.python.wanandroid

import android.support.v4.view.ViewPager
import android.widget.Toast
import com.python.wanandroid.adapter.MainViewPagerAdapter
import com.python.wanandroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    var mExitTime: Long = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        super.initView()
        activity_main_vp.adapter = MainViewPagerAdapter(supportFragmentManager)
    }

    override fun initListener() {
        super.initListener()

        activity_main_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                activity_main_bnv.menu.getItem(position).isChecked = true
            }

        })


    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar
                .fitsSystemWindows(true)
                .statusBarColor(R.color.colorPrimary)
                .init()
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - mExitTime < 2 * 1000) {
            super.onBackPressed()
            finish()
        } else {
            Toast.makeText(this, R.string.exit, Toast.LENGTH_SHORT).show()
            mExitTime = currentTime
        }
    }

}
