package com.python.wanandroid.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.python.wanandroid.ui.home.HomeFragment
import com.python.wanandroid.ui.mine.MineFragment
import com.python.wanandroid.ui.system.SystemFragment

/**
 * Created by Python on 2018/2/25.
 */
class MainViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> SystemFragment()
            2 -> MineFragment()
            else -> HomeFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}