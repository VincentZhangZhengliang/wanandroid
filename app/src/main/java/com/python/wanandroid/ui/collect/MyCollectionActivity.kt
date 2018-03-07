package com.python.wanandroid.ui.collect

import com.python.wanandroid.R
import com.python.wanandroid.base.BaseActivity
import com.python.wanandroid.ui.collect.adapter.CollectionAdapter
import com.python.wanandroid.ui.collect.model.CollectionListBean
import com.python.wanandroid.ui.collect.presenter.CollectionPresenter
import com.python.wanandroid.ui.collect.view.ICollectionView
import com.python.wanandroid.utils.Constant
import com.python.wanandroid.utils.Preference
import kotlinx.android.synthetic.main.activity_my_collection.*

class MyCollectionActivity : BaseActivity(), ICollectionView {

    var data: MutableList<CollectionListBean> = ArrayList()
    var adapter = CollectionAdapter(this@MyCollectionActivity, data)

    override fun toast(msg: String) {

    }

    override fun setView(datas: List<CollectionListBean>) {
        data.clear()
        data.addAll(datas)
        adapter.notifyDataSetChanged()
    }

    private val presenter = CollectionPresenter(this)
    private val username: String by Preference(Constant.USERNAME, "")
    private val password: String by Preference(Constant.PASSWORD, "")

    override fun getLayoutId(): Int {
        return R.layout.activity_my_collection
    }

    override fun initData() {
        super.initData()
        presenter.collectList(username, password)
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar.statusBarView(activity_my_collection_v).init()
    }

    override fun initListener() {
        super.initListener()
        activity_my_collection_toolbar.setNavigationOnClickListener { finish() }
    }

    override fun initView() {
        super.initView()
        activity_my_collection_toolbar.title = getString(R.string.my_collection)
        activity_my_collection_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        activity_my_collection_lv.adapter = adapter
    }

}
