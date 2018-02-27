package com.python.wanandroid.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.python.wanandroid.R
import com.python.wanandroid.ui.home.model.ArticleDatasBean
import com.zhy.autolayout.utils.AutoUtils

/**
 * Created by Vincent;
 * Created on 2018/2/27;
 * DSC:
 */
class HomeLvAdapter(var context: Context, var data: List<ArticleDatasBean>) : BaseAdapter() {

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
        holder.cbCollect.isChecked = data[position].collect
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
        val cbCollect = view.findViewById<CheckBox>(R.id.item_home_cb_collect)
    }

}