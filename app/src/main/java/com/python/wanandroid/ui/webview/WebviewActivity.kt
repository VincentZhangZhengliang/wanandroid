package com.python.wanandroid.ui.webview

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.view.View
import android.webkit.*
import com.python.wanandroid.R
import com.python.wanandroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*

class WebviewActivity : BaseActivity() {

    lateinit var url: String
    lateinit var title: String


    override fun getLayoutId(): Int {
        return R.layout.activity_webview
    }

    override fun initView() {
        super.initView()
        val settings = activity_webview_wv.settings
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.domStorageEnabled = true
        settings.allowFileAccess = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.blockNetworkImage = false
        settings.pluginState = WebSettings.PluginState.ON
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
//        activity_webview_wv.addJavascriptInterface(JavaScriptInterface(), "topzrt")
        activity_webview_wv.webViewClient = MyWebViewClient()
        activity_webview_wv.webChromeClient = MyWebChromeClient()
        activity_webview_wv.setDownloadListener(MyWebViewDownLoadListener())
        activity_webview_wv.loadUrl(url)

        activity_webview_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)

    }

    override fun initData() {
        super.initData()
        title = intent.getStringExtra("title")
        url = intent.getStringExtra("url")
    }


    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar.statusBarView(activity_webview_v).init()
    }

    private inner class MyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            //            loadingDialog.stopAnimator()
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

    }

    private inner class MyWebViewDownLoadListener : DownloadListener {

        override fun onDownloadStart(url: String, userAgent: String, contentDisposition: String,
                                     mimetype: String, contentLength: Long) {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

    }

    private inner class MyWebChromeClient : WebChromeClient() {

        private var mCustomView: View? = null
        private var mCustomViewCallback: CustomViewCallback? = null

        override fun onProgressChanged(view: WebView, newProgress: Int) {
            if (newProgress > 90) {
//                toolbar_title.text = title

            }
        }

        override fun onShowCustomView(view: View, callback: CustomViewCallback) {
            super.onShowCustomView(view, callback)
            if (mCustomView != null) {
                callback.onCustomViewHidden()
                return
            }
            mCustomView = view
            activity_webview_fl.addView(mCustomView)
            mCustomViewCallback = callback
            activity_webview_wv.visibility = View.GONE
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        override fun onHideCustomView() {
            if (mCustomView == null) {
                return
            }
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            activity_webview_wv.visibility = View.VISIBLE
            mCustomView!!.visibility = View.GONE
            activity_webview_fl.removeView(mCustomView)
            mCustomViewCallback!!.onCustomViewHidden()
            mCustomView = null
            super.onHideCustomView()
        }
    }


}
