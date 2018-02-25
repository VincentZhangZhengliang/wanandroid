package com.python.wanandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.python.wanandroid.adapter.MainViewPagerAdapter
import com.python.wanandroid.net.Api
import com.python.wanandroid.utils.LogUtil
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activity_main_vp.adapter = MainViewPagerAdapter(supportFragmentManager)

        Api.getArticleList(0).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<String>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                    override fun onComplete() {
                    }

                    override fun onNext(t: List<String>) {
                        Timber.e(t.toString())
                    }

                })
    }

}
