package com.python.wanandroid.ui.home


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
import com.python.wanandroid.utils.GlideImageLoader
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_banner.*

class HomeFragment : LazyLoadBaseFragment(), IHomeView {

    private val presenter = HomePresenter(this)
    private var images = ArrayList<String>()    //banner图片链接集合
    private var titles = ArrayList<String>()    //banner标题集合
    private var curPage = 0                     //分页参数
    private val articleList = ArrayList<ArticleDatasBean>()
    private var adapter: HomeLvAdapter = HomeLvAdapter(BaseApplication.instance(), articleList)

    override fun setBanner(data: List<BannerListBean>) {
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
        banner.start()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        val headerview = layoutInflater.inflate(R.layout.item_banner, fragment_home_lv, false)
        fragment_home_lv.addHeaderView(headerview)
        fragment_home_lv.adapter = adapter
    }

    override fun initListener() {

    }

    override fun initData() {
        presenter.getBanner()
        presenter.getArticleList(curPage, RefreshType.NONE)
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
    }

    override fun refreshView(data: ArticleDataBean, type: RefreshType) {
        curPage = data.curPage
        if (type != RefreshType.LOADMORE)
            articleList.clear()
        articleList.addAll(data.datas)
        adapter.notifyDataSetChanged()
    }

    override fun toast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

}
