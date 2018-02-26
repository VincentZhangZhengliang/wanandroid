package com.python.wanandroid.ui.home.model

/**
 * Created by Vincent;
 * Created on 2018/2/26;
 * DSC:
 */


data class ArticleBean(var data: ArticleDataBean, var errorCode: Int, var errorMsg: String)

data class ArticleDataBean(var curPage: Int, var datas: List<ArticleDatasBean>)

data class ArticleDatasBean(var apkLink: String,
                            var author: String,
                            var chapterId: Long,
                            var chapterName: String,
                            var collect: Boolean,
                            var courseId: Long,
                            var desc: String,
                            var envelopePic: String,
                            var id: Long,
                            var link: String,
                            var niceDate: String,
                            var origin: String,
                            var projectLink: String,
                            var publishTime: String,
                            var title: String,
                            var visible: Int,
                            var zan: Long)

/*
* {
    "data": {
        "curPage": 1,
        "datas": [
            {
                "apkLink": "",
                "author": "guoxiaoxing",
                "chapterId": 316,
                "chapterName": "系统源码分析",
                "collect": false,
                "courseId": 13,
                "desc": "Android 7.0 源码分析项目一期竣工啦~细分版本：N6F26U；分支：android-7.1.1_r28；版本：Nougat",
                "envelopePic": "http://www.wanandroid.com/blogimgs/c88958f2-b7eb-4559-95c4-feec3c52a55e.png",
                "id": 2410,
                "link": "http://www.wanandroid.com/blog/show/2051",
                "niceDate": "4小时前",
                "origin": "",
                "projectLink": "https://github.com/guoxiaoxing/android-open-source-project-analysis",
                "publishTime": 1519613090000,
                "title": "Android 7.0 源码分析项目一期竣工啦",
                "visible": 1,
                "zan": 0
            },
        ],
        "offset": 0,
        "over": false,
        "pageCount": 54,
        "size": 20,
        "total": 1063
    },
    "errorCode": 0,
    "errorMsg": ""
}
* */