package com.python.wanandroid.ui.collect

import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.widget.AbsListView
import com.python.wanandroid.R
import com.python.wanandroid.base.BaseActivity
import com.python.wanandroid.ui.collect.adapter.CollectionAdapter
import com.python.wanandroid.ui.collect.model.CollectionListBean
import com.python.wanandroid.ui.collect.presenter.CollectionPresenter
import com.python.wanandroid.ui.collect.view.ICollectionView
import com.python.wanandroid.utils.Constant
import com.python.wanandroid.utils.Preference
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_my_collection.*

class MyCollectionActivity : BaseActivity(), ICollectionView, SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {

    val TAG: String = "Joker"
    private val presenter = CollectionPresenter(this)
    private val username: String by Preference(Constant.USERNAME, "")
    private val password: String by Preference(Constant.PASSWORD, "")
    var data: MutableList<CollectionListBean> = ArrayList()
    var adapter = CollectionAdapter(this@MyCollectionActivity, data)

    private var curPage: Int = 0
    private var pageCount: Int = 0

    override fun toast(msg: String) {

    }

    override fun setView(datas: List<CollectionListBean>, curPage: Int, pageCount: Int) {
        if (curPage - 1 == 0)
            data.clear()
        data.addAll(datas)
        adapter.notifyDataSetChanged()
        this.pageCount = pageCount
        this.curPage = curPage
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_my_collection
    }

    override fun initData() {
        super.initData()
        presenter.collectList(0, username, password)
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar.statusBarView(activity_my_collection_v).init()
    }

    override fun initListener() {
        super.initListener()
        activity_my_collection_toolbar.setNavigationOnClickListener { finish() }
        activity_my_collection_srl.setOnRefreshListener(this)
        activity_my_collection_lv.setOnScrollListener(this)
    }

    override fun initView() {
        super.initView()
        activity_my_collection_toolbar.title = getString(R.string.my_collection)
        activity_my_collection_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        activity_my_collection_lv.adapter = adapter
    }

    override fun refreshFinish() {
        activity_my_collection_srl.isRefreshing = false
    }

    override fun onRefresh() {
        presenter.collectList(0, username, password)
    }

    override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {

        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            val lastVisibleItemView = activity_my_collection_lv.getChildAt(totalItemCount - firstVisibleItem - 1)
            if (lastVisibleItemView != null && lastVisibleItemView.bottom == view?.height) {
                if (curPage < pageCount) {
                    presenter.collectList(curPage, username, password)
                }
            }
        }
    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
    }

    override fun onResume() {
        MobclickAgent.onPageStart("我的收藏")
        super.onResume()
    }

    override fun onPause() {
        MobclickAgent.onPageEnd("我的收藏")
        super.onPause()
    }

}
