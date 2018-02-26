package com.python.wanandroid.ui.home


import android.widget.Toast
import com.python.wanandroid.R
import com.python.wanandroid.base.LazyLoadBaseFragment
import com.python.wanandroid.net.Api
import com.python.wanandroid.ui.home.model.ArticleDataBean
import com.python.wanandroid.ui.home.view.IHomeView
import com.python.wanandroid.utils.GlideImageLoader
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber


class HomeFragment : LazyLoadBaseFragment(), IHomeView {

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

        Api.getBanner().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<String> {
                    override fun onNext(t: String) {
                        Timber.e(t)
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }
                })
    }

    override fun refreshView(data: ArticleDataBean) {

    }

    override fun toast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

}
