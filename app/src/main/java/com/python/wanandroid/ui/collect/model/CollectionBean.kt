package com.python.wanandroid.ui.collect.model

/**
 * @author Python
 * @date 2018/3/4
 * @desc
 */
data class CollectionBean(var data: CollectionDataBean, var errorCode: Int, var errorMsg: String)

data class CollectionDataBean(var curPage: Int, var datas: List<CollectionListBean>, var offset: Int, var over: Boolean, var pageCount: Int, var size: Int, var total: Int)

data class CollectionListBean(var author: String, var chapterId: Long, var chapterName: String, var courseId: Long, var desc: String, var envelopePic: String, var id: Long, var link: String, var niceDate: String, var origin: String, var originId: Long, var publishTime: Long,
                              var title: String, var userId: Long, var visible: Int, var zan: Long)