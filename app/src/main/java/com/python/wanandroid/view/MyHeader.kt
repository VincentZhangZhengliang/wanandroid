package com.python.wanandroid.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.liaoinstan.springview.container.BaseHeader
import com.python.wanandroid.R

class MyHeader : BaseHeader() {

    var textView: TextView? = null
    var imageView: ImageView? = null
    var progressBar: ProgressBar? = null
    var linearLayout: LinearLayout? = null

    //手指拖拽过程中每次经过临界点时回调，upORdown是向上经过还是向下经过
    override fun onLimitDes(rootView: View?, upORdown: Boolean) {
//        if (!upORdown) {
//            imageView?.visibility = View.GONE
//            textView?.visibility = View.VISIBLE
//            progressBar?.visibility = View.VISIBLE
//            linearLayout?.visibility = View.VISIBLE
//        } else {
//            imageView?.visibility = View.VISIBLE
//            linearLayout?.visibility = View.GONE
//            textView?.visibility = View.GONE
//            progressBar?.visibility = View.GONE
//        }
    }

    //头部已经全部弹回时回调
    override fun onFinishAnim() {
        linearLayout?.visibility = View.GONE
        textView?.visibility = View.GONE
        progressBar?.visibility = View.GONE
        imageView?.visibility = View.VISIBLE
    }

    //拉动超过临界点后松开时回调
    override fun onStartAnim() {
        imageView?.visibility = View.GONE
        textView?.visibility = View.VISIBLE
        progressBar?.visibility = View.VISIBLE
        linearLayout?.visibility = View.VISIBLE
    }

    //手指拖拽过程中不断回调，dy为拖拽的距离，可以根据拖动的距离添加拖动过程动画
    override fun onDropAnim(rootView: View?, dy: Int) {

    }

    //获取Header
    override fun getView(inflater: LayoutInflater?, viewGroup: ViewGroup?): View {
        val view = inflater?.inflate(R.layout.layout_springview_header, viewGroup, true)
        textView = view?.findViewById(R.id.layout_springview_header_tv)
        imageView = view?.findViewById<ImageView>(R.id.layout_springview_header_iv)
        progressBar = view?.findViewById(R.id.layout_springview_header_progressbar)
        linearLayout = view?.findViewById<LinearLayout>(R.id.layout_springview_header_ll)
        return view!!
    }

    //拖拽开始前回调
    override fun onPreDrag(rootView: View?) {

    }

}