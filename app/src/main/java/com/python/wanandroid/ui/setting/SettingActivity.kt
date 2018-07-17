package com.python.wanandroid.ui.setting

import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.support.v4.app.NotificationCompat
import cn.jpush.android.api.JPushInterface
import com.python.wanandroid.R
import com.python.wanandroid.base.BaseActivity
import com.python.wanandroid.ui.setting.event.SignOutEvent
import com.python.wanandroid.ui.setting.presenter.SettingPresenter
import com.python.wanandroid.ui.setting.view.ISettingView
import com.python.wanandroid.utils.Constant
import com.python.wanandroid.utils.Preference
import kotlinx.android.synthetic.main.activity_setting.*
import org.greenrobot.eventbus.EventBus
import timber.log.Timber
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import com.python.wanandroid.utils.Constant.CHANNEL_ID
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v4.app.NotificationManagerCompat
import android.widget.Toast
import com.python.wanandroid.MainActivity
import com.python.wanandroid.receiver.NotificationReceiver
import com.python.wanandroid.view.dialog.TimePickerDialog
import com.umeng.analytics.MobclickAgent


class SettingActivity : BaseActivity(), ISettingView, TimePickerDialog.OnConfirmListener {


    val presenter = SettingPresenter(this@SettingActivity)
    var isLogin: Boolean by Preference(Constant.LOGIN, false)
    var name: String by Preference(Constant.USERNAME, "")
    var psw: String by Preference(Constant.PASSWORD, "")
    var spDomain: String by Preference(Constant.DOMAIN, "")

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar.statusBarView(view4).init()
    }

    override fun initView() {
        super.initView()
        activity_setting_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        activity_setting_toolbar.title = getString(R.string.setting)
    }

    override fun initData() {
        super.initData()
        activity_setting_btn_signout.isEnabled = isLogin
    }

    override fun initListener() {
        super.initListener()
        activity_setting_toolbar.setNavigationOnClickListener { finish() }
        activity_setting_btn_signout.setOnClickListener {
            presenter.signOut()
        }
        textView4.setOnClickListener {
            var num = 0
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("noti", "lalalala")
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)


            val snoozeIntent = Intent(this, NotificationReceiver::class.java)
            snoozeIntent.action = Constant.ACTION_SNOOZE
            snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0)
            val snoozePendingIntent = PendingIntent.getBroadcast(this, 0, snoozeIntent, 0)

            val builder = NotificationCompat.Builder(this, Constant.CHANNEL_ID)
            builder.setSmallIcon(R.drawable.ic_arrow_back_black_24dp)
            builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_arrow_back_black_24dp))
            builder.setContentTitle("This is a ttile")
            builder.setContentText("this is content")
            builder.setStyle(NotificationCompat.BigTextStyle().bigText("Much longer text that cannot fit one line...Much longer text that cannot fit one line...Much longer text that cannot fit one line...Much longer text that cannot fit one line..."))
            builder.priority = NotificationCompat.PRIORITY_DEFAULT
            builder.setContentIntent(pendingIntent)
            builder.setAutoCancel(true)
            builder.addAction(R.drawable.ic_arrow_back_black_24dp, "snooze", snoozePendingIntent)
            builder.addAction(R.drawable.ic_arrow_back_black_24dp, "snooze", snoozePendingIntent)
            builder.setNumber(++num)
            val notification = builder.build()

            val manager = NotificationManagerCompat.from(this@SettingActivity)
            manager.notify(0, notification)


        }
        sw_push_message.setOnCheckedChangeListener { _, isChecked ->
            presenter.openMessagePush(this, isChecked)
        }
        sw_message_no_disturb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val dialog = TimePickerDialog.getInstance()
                dialog.show(supportFragmentManager, "TimePickerDialog")
            } else {
                presenter.messageNoDisturb(this)
            }

        }
    }

    override fun onConfirm(startHour: Int, startMinute: Int, endHour: Int, endMinute: Int) {
        presenter.messageNoDisturb(this, startHour, startMinute, endHour, endMinute)
    }

    override fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun signOutSuccess(name: String) {
        EventBus.getDefault().post(SignOutEvent(true))
        finish()
        JPushInterface.deleteAlias(this, Constant.JPUSH_SEQUENCE)
    }

    override fun onResume() {
        MobclickAgent.onPageStart("设置") //手动统计页面("SplashScreen"为页面名称，可自定义)
        super.onResume()
    }

    override fun onPause() {
        MobclickAgent.onPageEnd("设置")
        super.onPause()
    }


}
