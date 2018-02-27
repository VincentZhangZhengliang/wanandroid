package com.python.wanandroid.ui.home


import android.widget.Toast
import com.python.wanandroid.R
import com.python.wanandroid.base.LazyLoadBaseFragment
import com.python.wanandroid.ui.home.model.ArticleDataBean
import com.python.wanandroid.ui.home.model.BannerListBean
import com.python.wanandroid.ui.home.presenter.HomePresenter
import com.python.wanandroid.ui.home.view.IHomeView
import com.python.wanandroid.utils.GlideImageLoader
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : LazyLoadBaseFragment(), IHomeView {

    val presenter = HomePresenter(this)

    override fun setBanner(data: List<BannerListBean>) {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        //设置图片加载器
        fragment_home_banner.setImageLoader(GlideImageLoader())
//        //设置图片集合
//        fragment_home_banner.setImages(images)
//        //banner设置方法全部调用完毕时最后调用
//        fragment_home_banner.start()
    }

    override fun initListener() {

    }

    override fun initData() {
        presenter.getBanner()
    }

    override fun refreshView(data: ArticleDataBean) {

    }

    override fun toast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

}
