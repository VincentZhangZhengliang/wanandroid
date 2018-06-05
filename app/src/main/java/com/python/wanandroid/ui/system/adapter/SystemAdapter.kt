package com.python.wanandroid.ui.system.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.python.wanandroid.R
import com.python.wanandroid.ui.system.model.TreeDataBean

/**
 * Created by Vincent;
 * Created on 2018/2/27;
 * DSC:
 */
class SystemAdapter(var context: Context, var data: List<TreeDataBean>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_system, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        holder.tvName.text = data[position].name
        holder.tvChildren.apply {
            val sb = StringBuilder()
            data[position].children.forEach {
                sb.append(it.name).append("  ")
            }
            text = sb.toString()
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
        val tvName = view.findViewById<TextView>(R.id.item_system_tv_name)
        val tvChildren = view.findViewById<TextView>(R.id.item_system_tv_children)
    }

}