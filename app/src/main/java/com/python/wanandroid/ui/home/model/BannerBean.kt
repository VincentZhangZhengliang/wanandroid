package com.python.wanandroid.ui.home.model

/**
 * @author Python
 * @date 2018/2/26
 * @desc
 */


data class BannerBean(var data: List<BannerListBean>, var errorCode: Int, var errorMsg: String)

data class BannerListBean(var desc: String,
                          var id: Long,
                          var imagePath: String,
                          var isVisible: Int,
                          var order: Int,
                          var title: String,
                          var type: Int,
                          var url: String)