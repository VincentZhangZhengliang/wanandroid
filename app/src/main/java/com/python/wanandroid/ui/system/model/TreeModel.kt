package com.python.wanandroid.ui.system.model

import java.io.Serializable

/**
 * Created by Vincent;
 * Created on 2018/2/27;
 * DSC:
 */


data class TreeBean(var data : List<TreeDataBean>, var errorCode : Int, var errorMsg : String)

data class TreeDataBean(var children : List<TreeChildrenBean>, var courseId : Long, var id : Long, var name : String, var order : Long, var parentChapterId : Long, var visible : Int)

data class TreeChildrenBean(var courseId : Long, var id : Long, var name : String, var order : String, var parentChapterId : String, var visible : Int) : Serializable

