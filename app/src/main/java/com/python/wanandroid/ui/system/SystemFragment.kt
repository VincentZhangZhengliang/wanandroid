package com.python.wanandroid.ui.system


import android.support.v4.app.Fragment
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.python.wanandroid.R
import com.python.wanandroid.base.BaseApplication
import com.python.wanandroid.base.LazyLoadBaseFragment
import com.python.wanandroid.ui.system.adapter.SystemAdapter
import com.python.wanandroid.ui.system.model.TreeDataBean
import com.python.wanandroid.ui.system.presenter.SystemPresenter
import com.python.wanandroid.ui.system.view.ISystemView
import kotlinx.android.synthetic.main.fragment_system.*


/**
 * A simple [Fragment] subclass.
 */
class SystemFragment : LazyLoadBaseFragment(), ISystemView {

    override fun toast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun setView(data: List<TreeDataBean>) {
        fragment_system_lv.adapter = SystemAdapter(BaseApplication.instance(), data)
    }

    val presenter = SystemPresenter(this)

    override fun getLayoutId(): Int {
        return R.layout.fragment_system
    }

    override fun initView() {
        ImmersionBar.with(this).statusBarView(fragment_system_v).init()
    }

    override fun initListener() {
    }

    override fun initData() {
        presenter.getTree()
    }

//    override fun initImmersionBar() {
//        super.initImmersionBar()
//    }

}
