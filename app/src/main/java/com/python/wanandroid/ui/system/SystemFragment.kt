package com.python.wanandroid.ui.system


import android.content.Intent
import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.python.wanandroid.R
import com.python.wanandroid.base.BaseApplication
import com.python.wanandroid.base.LazyLoadBaseFragment
import com.python.wanandroid.ui.system.adapter.SystemAdapter
import com.python.wanandroid.ui.system.model.TreeDataBean
import com.python.wanandroid.ui.system.presenter.SystemPresenter
import com.python.wanandroid.ui.system.view.ISystemView
import com.python.wanandroid.ui.system_cate.SystemCateActivity
import kotlinx.android.synthetic.main.fragment_system.*
import java.io.Serializable


/**
 * A simple [Fragment] subclass.
 */
class SystemFragment : LazyLoadBaseFragment(), ISystemView {

    var mData = mutableListOf<TreeDataBean>()
    override fun toast(msg : String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun setView(data : List<TreeDataBean>) {
        fragment_system_lv.adapter = SystemAdapter(BaseApplication.instance(), data)
        mData.clear()
        mData.addAll(data)
    }

    val presenter = SystemPresenter(this)

    override fun getLayoutId() : Int {
        return R.layout.fragment_system
    }

    override fun initView() {

    }

    override fun initToolbar() {
        super.initToolbar()
        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.setSupportActionBar(fragment_system_toolbar)
        setHasOptionsMenu(true)
        fragment_system_toolbar.apply {
            title = getString(R.string.system)
            setTitleTextColor(Color.WHITE)
        }
    }

    override fun initListener() {
        fragment_system_lv.setOnItemClickListener { _, _, position, _ ->
            val name = mData[position].name
            startActivity(Intent(activity, SystemCateActivity::class.java).putExtra("cate", name).putExtra("list", mData[position].children as Serializable))
        }
    }

    override fun initData() {
        presenter.getTree()
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).statusBarView(fragment_system_v).init()
    }

}
