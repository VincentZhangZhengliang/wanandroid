package com.python.wanandroid.base


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 *  lazyLoad baseFragment
 */
abstract class LazyLoadBaseFragment : Fragment() {

    var isViewCreated: Boolean = false
    var isUIVisible: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        isViewCreated = true
        lazyLoad()
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initListener()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initListener()

    abstract fun initData()

    protected open fun initImmersionBar() {

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isUIVisible = isVisibleToUser
        if (isVisibleToUser) {
            lazyLoad()
        }
    }

    /**
     * 懒加载
     */
    private fun lazyLoad() {
        if (isViewCreated && isUIVisible) {
            initData()
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false
            isUIVisible = false
        }
    }

}
