package com.example.kotlin_practice_app.client

import com.example.kotlin_practice_app.activity.LoginActivity
import com.example.kotlin_practice_app.activity.MainActivity
import com.example.kotlin_practice_app.contant.AppConstant.APP_TOKEN
import com.example.kotlin_practice_app.contant.ClientConstant.BASE_URL
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.utils.OkHttpUtil
import com.example.kotlin_practice_app.vo.AppLoginReqVo
import okhttp3.Callback
import okhttp3.Headers

class BackendClient {
    companion object {
        fun login(account: String, pwd: String, callback: Callback) {
            val url = BASE_URL + "/app/authentication/login"
            val requestBody = AppLoginReqVo(account, pwd)
            OkHttpUtil.asyPost(url, GsonUtil.toJson(requestBody), callback)
        }

        fun myInfo(token: String, callback: Callback) {
            val url = BASE_URL + "/app/user/my-info"
            OkHttpUtil.asyPost(url, Headers.headersOf(APP_TOKEN, token), callback)
        }
    }
}