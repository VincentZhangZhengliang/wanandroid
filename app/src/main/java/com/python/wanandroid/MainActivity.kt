package com.python.wanandroid

import android.support.v4.view.ViewPager
import android.widget.Toast
import com.python.wanandroid.adapter.MainViewPagerAdapter
import com.python.wanandroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    var mExitTime : Long = 0

    override fun getLayoutId() : Int {
        return R.layout.activity_main
    }

    override fun initView() {
        super.initView()
        activity_main_vp.adapter = MainViewPagerAdapter(supportFragmentManager)
        activity_main_vp.offscreenPageLimit = 2
        setSwipeBackEnable(false)
    }

    override fun initListener() {
        super.initListener()

        activity_main_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state : Int) {
            }

            override fun onPageScrolled(position : Int, positionOffset : Float, positionOffsetPixels : Int) {
            }

            override fun onPageSelected(position : Int) {
                activity_main_bnv.menu.getItem(position).isChecked = true
                when (position) {
                    0 -> mImmersionBar.statusBarDarkFont(true, 0.5f).navigationBarColor(R.color.colorPrimary).init()
                }
            }
        })

        activity_main_bnv.setOnNavigationItemSelectedListener {
            var result = true
            when (it.itemId) {
                R.id.id_menu_home   -> {
                    activity_main_vp.currentItem = 0
                    result = true
                }
                R.id.id_menu_system -> {
                    activity_main_vp.currentItem = 1
                    result = true
                }
                R.id.id_menu_mine   -> {
                    activity_main_vp.currentItem = 2
                    result = true
                }
            }
            result
        }

    }

    override fun initImmersionBar() {
        super.initImmersionBar()
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
