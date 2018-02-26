package com.python.wanandroid.ui.home


import android.widget.Toast
import com.python.wanandroid.R
import com.python.wanandroid.base.LazyLoadBaseFragment
import com.python.wanandroid.ui.home.model.ArticleDataBean
import com.python.wanandroid.ui.home.view.IHomeView


class HomeFragment : LazyLoadBaseFragment(), IHomeView {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {



    }

    override fun initListener() {
    }

    override fun initData() {
    }

    override fun refreshView(data: ArticleDataBean) {

    }

    override fun toast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

}
