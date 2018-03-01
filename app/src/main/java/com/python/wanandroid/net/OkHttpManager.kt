package com.python.wanandroid.net

import com.python.wanandroid.utils.Constant
import com.python.wanandroid.utils.Preference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author Python
 * @date 2018/2/25
 * @desc   OkHttp的管理类
 */
object OkHttpManager {

    private const val TAG = "RetrofitHelper"
    private const val CONTENT_PRE = "OkHttp: "
    private const val SAVE_USER_LOGIN_KEY = "user/login"
    private const val SAVE_USER_REGISTER_KEY = "user/register"
    private const val SET_COOKIE_KEY = "set-cookie"
    private const val COOKIE_NAME = "Cookie"
    private const val CONNECT_TIMEOUT = 30L
    private const val READ_TIMEOUT = 10L

    var login: Boolean by Preference(Constant.LOGIN, false)

    fun getClient(): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor {
                    // get response cookie
                    val request = it.request()
                    val response = it.proceed(request)
                    val requestUrl = request.url().toString()
                    val domain = request.url().host()
                    // set-cookie maybe has multi, login to save cookie
                    val cookies = response.headers(SET_COOKIE_KEY)
                    val cookie = encodeCookie(cookies)
                    val spDomain: String by Preference(domain, "")
                    login = cookie == spDomain
                    //登录或者注册成功后记录Cookies
                    if ((requestUrl.contains(SAVE_USER_LOGIN_KEY) || requestUrl.contains(SAVE_USER_REGISTER_KEY)) && !response.headers(SET_COOKIE_KEY).isEmpty()) {
                        saveCookie(requestUrl, domain, cookie)
                    }
                    response
                }
                .addInterceptor {
                    // set request cookie
                    val request = it.request()
                    val builder = request.newBuilder()
                    val domain = request.url().host()
                    // get domain cookie
                    if (domain.isNotEmpty()) {
                        val spDomain: String by Preference(domain, "")
                        val cookie: String = if (spDomain.isNotEmpty()) spDomain else ""
                        if (cookie.isNotEmpty()) {
                            builder.addHeader(COOKIE_NAME, cookie)
                        }
                    }
                    it.proceed(builder.build())
                }
                .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                    Timber.e(it)
                }).apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
    }

    private fun saveCookie(url: String?, domain: String?, cookies: String) {
        url ?: return
        var spUrl: String by Preference(url, cookies)
        spUrl = cookies
        domain ?: return
        var spDomain: String by Preference(domain, cookies)
        spDomain = cookies

    }

    private fun encodeCookie(cookies: MutableList<String>): String {
        var sb: StringBuilder = StringBuilder()
        for (cookie in cookies) {
            sb.append(cookie)
        }
        return sb.toString()
    }


}