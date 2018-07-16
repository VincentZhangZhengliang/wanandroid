package com.python.wanandroid.ui.system_cate

import com.python.wanandroid.R
import com.python.wanandroid.base.BaseActivity
import com.python.wanandroid.ui.system.model.TreeChildrenBean
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_system_detail.*

class SystemCateActivity : BaseActivity() {

    lateinit var cate : String
    var mTabList = mutableListOf<TreeChildrenBean>()

    override fun getLayoutId() : Int {
        return R.layout.activity_system_detail
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar.statusBarView(activity_system_cate_v).init()
    }

    override fun initListener() {
        super.initListener()
        activity_system_cate_toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initView() {
        super.initView()
        activity_system_cate_toolbar.apply {
            setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            title = cate
        }
        mTabList.forEachIndexed { _, treeChildrenBean ->
            activity_system_cate_tablayoyt.addTab(activity_system_cate_tablayoyt.newTab().setText(treeChildrenBean.name))
        }
    }

    override fun initData() {
        super.initData()
        cate = intent.getStringExtra("cate")
        val tabList = intent.getSerializableExtra("list") as MutableList<TreeChildrenBean>
        mTabList.clear()
        mTabList.addAll(tabList)
    }

    override fun onResume() {
        MobclickAgent.onPageStart("系统分类")
        super.onResume()
    }

    override fun onPause() {
        MobclickAgent.onPageEnd("系统分类")
        super.onPause()
    }


}
