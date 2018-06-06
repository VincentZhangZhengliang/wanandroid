package com.python.wanandroid.ui.home


import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.widget.AbsListView
import android.widget.Toast
import com.python.wanandroid.R
import com.python.wanandroid.base.BaseApplication
import com.python.wanandroid.base.LazyLoadBaseFragment
import com.python.wanandroid.ui.home.adapter.HomeLvAdapter
import com.python.wanandroid.ui.home.model.ArticleDataBean
import com.python.wanandroid.ui.home.model.ArticleDatasBean
import com.python.wanandroid.ui.home.model.BannerListBean
import com.python.wanandroid.ui.home.model.RefreshType
import com.python.wanandroid.ui.home.presenter.HomePresenter
import com.python.wanandroid.ui.home.view.IHomeView
import com.python.wanandroid.ui.setting.event.SignOutEvent
import com.python.wanandroid.ui.signin.event.SignInEvent
import com.python.wanandroid.ui.webview.WebviewActivity
import com.python.wanandroid.utils.Constant
import com.python.wanandroid.utils.GlideImageLoader
import com.python.wanandroid.utils.Preference
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.item_banner.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class HomeFragment : LazyLoadBaseFragment(), IHomeView, SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {

    private val presenter = HomePresenter(this)
    private var images = ArrayList<String>()    //banner图片链接集合
    private var titles = ArrayList<String>()    //banner标题集合
    private var curPage = 0                     //分页参数
    private var pageCount : Int = 0
    private val articleList = ArrayList<ArticleDatasBean>()
    private var adapter : HomeLvAdapter = HomeLvAdapter(BaseApplication.instance(), articleList)
    var login : Boolean by Preference(Constant.LOGIN, false)

    override fun setBanner(data : List<BannerListBean>) {
        images.clear()
        titles.clear()
        data.forEach {
            images.add(it.imagePath)
            titles.add(it.title)
        }
        banner.setImageLoader(GlideImageLoader())
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        banner.setImages(images)
        banner.setBannerTitles(titles)
        banner.setOnBannerListener { position : Int ->
            val intent = Intent(activity, WebviewActivity::class.java)
            intent.putExtra("title", data[position].title)
            intent.putExtra("url", data[position].url)
            startActivity(intent)
        }
        banner.start()
    }

    override fun getLayoutId() : Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        val headerview = layoutInflater.inflate(R.layout.item_banner, fragment_home_lv, false)
        fragment_home_lv.addHeaderView(headerview)
        fragment_home_lv.adapter = adapter
    }

    override fun initListener() {
        fragment_home_srl.setOnRefreshListener(this)
        fragment_home_lv.setOnScrollListener(this)
    }

    override fun initData() {
        EventBus.getDefault().register(this)
        presenter.getBanner()
        presenter.getArticleList(curPage, RefreshType.NONE)
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
    }

    override fun refreshView(data : ArticleDataBean, type : RefreshType) {
        this.curPage = data.curPage
        this.pageCount = data.pageCount
        if (type != RefreshType.LOADMORE) articleList.clear()
        articleList.addAll(data.datas)
        adapter.notifyDataSetChanged()
    }

    override fun toast(msg : String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onRefresh() {
        presenter.getBanner()
        presenter.getArticleList(0, RefreshType.REFRESH)
    }

    override fun refreshFinish() {
        fragment_home_srl.isRefreshing = false
    }

    override fun onScroll(view : AbsListView?, firstVisibleItem : Int, visibleItemCount : Int, totalItemCount : Int) {
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            val lastVisibleItemView = fragment_home_lv.getChildAt(totalItemCount - firstVisibleItem - 1)
            if (lastVisibleItemView != null && lastVisibleItemView.bottom == view?.height) {
                if (curPage < pageCount) {
                    presenter.getArticleList(curPage, RefreshType.LOADMORE)
                } else {
                    toast("没有更多数据~")
                }
            }
        }
    }

    override fun onScrollStateChanged(view : AbsListView?, scrollState : Int) {
    }

    @Subscribe
    fun onSignOutEvent(signOutEvent : SignOutEvent) {
        presenter.getArticleList(0, RefreshType.REFRESH)
    }

    @Subscribe
    fun onSignInEvent(signInEvent : SignInEvent) {
        presenter.getArticleList(0, RefreshType.REFRESH)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}
