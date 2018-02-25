package com.python.wanandroid.utils

import android.util.Log

/**
 * @author Python
 * @date 2018/2/25
 * @desc
 */
class LogUtil {

    companion object {

        private val LOGV = true
        private val LOGD = true
        private val LOGI = true
        private val LOGW = true
        private val LOGE = true
        private val TAG = "Vincent"

        fun v(msg: String, tag: String = TAG) {
            if (LOGV) {
                log(Level.V, tag, msg)
            }
        }

        fun d(msg: String, tag: String = TAG) {
            if (LOGD) {
                log(Level.V, tag, msg)
            }
        }

        fun i(msg: String, tag: String = TAG) {
            if (LOGI) {
                log(Level.V, tag, msg)
            }
        }

        fun w(msg: String, tag: String = TAG) {
            if (LOGW) {
                log(Level.V, tag, msg)
            }
        }

        fun e(msg: String, tag: String = TAG) {
            if (LOGE) {
                log(Level.V, tag, msg)
            }
        }

        fun log(level: Level, tag: String, msg: String) {
            var index = 0
            val maxLength = 4000
            var sub: String
            while (index < msg.length) {
                // java的字符不允许指定超过总的长度end
                if (msg.length <= index + maxLength) {
                    sub = msg.substring(index)
                } else {
                    sub = msg.substring(index, maxLength)
                }
                index += maxLength
                when (level) {
                    Level.V -> Log.v(tag, sub.trim())
                    Level.D -> Log.v(tag, sub.trim())
                    Level.I -> Log.v(tag, sub.trim())
                    Level.W -> Log.v(tag, sub.trim())
                    Level.E -> Log.v(tag, sub.trim())
                }
            }
        }
    }

    enum class Level {
        V,
        D,
        I,
        W,
        E
    }

}