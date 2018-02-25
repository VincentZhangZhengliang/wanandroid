package com.python.wanandroid.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.python.wanandroid.R
import com.zhy.autolayout.AutoLayoutActivity

class BaseActivity : AutoLayoutActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

}
