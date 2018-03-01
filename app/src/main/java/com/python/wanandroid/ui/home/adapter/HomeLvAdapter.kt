package com.python.wanandroid.ui.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.python.wanandroid.R
import com.python.wanandroid.net.Api
import com.python.wanandroid.ui.home.model.ArticleDatasBean
import com.python.wanandroid.ui.webview.WebviewActivity
import com.python.wanandroid.utils.Constant
import com.python.wanandroid.utils.Preference
import com.zhy.autolayout.utils.AutoUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Vincent;
 * Created on 2018/2/27;
 * DSC:
 */
class HomeLvAdapter(var context: Context, var data: List<ArticleDatasBean>) : BaseAdapter() {


    var login: Boolean by Preference(Constant.LOGIN, false)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        AutoUtils.autoSize(view)
//        Glide.with(context).load(data[position].envelopePic).into(holder.ivPic)
        holder.tvAuthor.text = data[position].author
        holder.tvNiceDate.text = data[position].niceDate
        holder.tvContent.text = data[position].title
        if (data[position].collect)
            holder.cbCollect.setBackgroundResource(R.drawable.ic_favorite_24dp)
        else
            holder.cbCollect.setBackgroundResource(R.drawable.ic_favorite_black_24dp)

        holder.cbCollect.setOnClickListener {
            if (data[position].collect) {
                //TODO : 取消收藏
                Api.uncollect2(data[position].id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            if (it.errorCode == 0) {
                                holder.cbCollect.setBackgroundResource(R.drawable.ic_favorite_black_24dp)
                                data[position].collect = false
                                Toast.makeText(context, "取消收藏成功", Toast.LENGTH_SHORT).show()
                            }
                        }

            } else {
                Api.collect(data[position].id).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            if (it.errorCode == 0) {
                                holder.cbCollect.setBackgroundResource(R.drawable.ic_favorite_24dp)
                                data[position].collect = true
                                Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, it.errorMsg, Toast.LENGTH_SHORT).show()
                            }
                        }
            }
        }

        holder.view.setOnClickListener {
            val intent = Intent(context, WebviewActivity::class.java)
            intent.putExtra("title", data[position].title)
            intent.putExtra("url", data[position].link)
            context.startActivity(intent)
        }

        return view
    }


    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

    class ViewHolder(var view: View) {
        val ivPic = view.findViewById<ImageView>(R.id.item_home_iv_pic)
        val tvAuthor = view.findViewById<TextView>(R.id.item_home_tv_author)
        val tvNiceDate = view.findViewById<TextView>(R.id.item_home_tv_niceDate)
        val tvContent = view.findViewById<TextView>(R.id.item_home_tv_content)
        val cbCollect = view.findViewById<ImageView>(R.id.item_home_cb_collect)
    }

}