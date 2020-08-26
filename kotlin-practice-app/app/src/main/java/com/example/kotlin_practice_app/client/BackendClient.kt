package com.example.kotlin_practice_app.client

import com.example.kotlin_practice_app.contant.AppConstant.APP_TOKEN
import com.example.kotlin_practice_app.contant.ClientConstant.BASE_URL
import com.example.kotlin_practice_app.manager.TokenManager
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.utils.OkHttpUtil
import com.example.kotlin_practice_app.vo.AppLoginReqVo
import com.example.kotlin_practice_app.vo.AppRegisteredReqVo
import okhttp3.Callback
import okhttp3.Headers

class BackendClient {
    companion object {

        /**
         * 注册
         * @param account 账号
         * @param pwd 密码
         * @param nickname 昵称
         */
        fun registered(account: String, pwd: String, nickname: String, callback: Callback) {
            val url = "$BASE_URL/app/authentication/registered"
            val requestBody = AppRegisteredReqVo(account, pwd, nickname)
            OkHttpUtil.asyPost(url, GsonUtil.toJson(requestBody), callback)

        }

        /**
         * 登陆
         * @param account 账号
         * @param pwd 密码
         */
        fun login(account: String, pwd: String, callback: Callback) {
            val url = "$BASE_URL/app/authentication/login"
            val requestBody = AppLoginReqVo(account, pwd)
            OkHttpUtil.asyPost(url, GsonUtil.toJson(requestBody), callback)
        }

        /**
         * 我的用户信息
         */
        fun myInfo(callback: Callback) {
            val url = "$BASE_URL/app/user/my-info"
            OkHttpUtil.asyPost(url, Headers.headersOf(APP_TOKEN, TokenManager.getToken()), callback)
        }
    }
}