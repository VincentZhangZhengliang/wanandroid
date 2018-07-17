package com.python.wanandroid.receiver

import android.content.Context
import android.content.Intent
import com.umeng.message.UmengMessageService
import org.json.JSONObject
import com.umeng.message.entity.UMessage
import org.android.agoo.common.AgooConstants
import timber.log.Timber


class MyUmengMessageService : UmengMessageService() {

    override fun onMessage(p0: Context?, intent: Intent?) {
        val message = intent?.getStringExtra(AgooConstants.MESSAGE_BODY)
        val msg = UMessage(JSONObject(message))
        Timber.e("msg = $msg")
        val title = msg.title
        val alias = msg.alias
        val custom = msg.custom
        Timber.e("title = $title,alias = $alias, custom = $custom")
        val extra = msg.extra

    }
}