package com.example.kotlin_practice_app.client

import com.example.kotlin_practice_app.contant.AppConstant.APP_TOKEN
import com.example.kotlin_practice_app.contant.ClientConstant.BASE_URL
import com.example.kotlin_practice_app.manager.UserInfoManager
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.utils.OkHttpUtil
import com.example.kotlin_practice_app.vo.AppLoginReqVo
import com.example.kotlin_practice_app.vo.AppMyGoodsPageReqVo
import com.example.kotlin_practice_app.vo.AppMyGoodsUpdateReqVo
import com.example.kotlin_practice_app.vo.AppRegisteredReqVo
import okhttp3.Callback
import okhttp3.Headers

object BackendClient {

    object AppAuthentication {

        private const val preUrl = "/app/authentication"

        /**
         * 注册
         */
        fun registered(requestBody: AppRegisteredReqVo, callback: Callback) {
            val url = "$BASE_URL$preUrl/registered"
            OkHttpUtil.asyPost(url, GsonUtil.toJson(requestBody), callback)

        }

        /**
         * 登陆
         */
        fun login(requestBody: AppLoginReqVo, callback: Callback) {
            val url = "$BASE_URL$preUrl/login"
            OkHttpUtil.asyPost(url, GsonUtil.toJson(requestBody), callback)
        }
    }

    object AppUser {

        private const val preUrl = "/app/user/"

        /**
         * 我的用户信息
         */
        fun myInfo(token: String, callback: Callback) {
            val url = "$BASE_URL$preUrl/my-info"
            OkHttpUtil.asyPost(url, Headers.headersOf(APP_TOKEN, token), callback)
        }
    }

    object AppGoods {

        private const val preUrl = "/app/goods"

        /**
         * 我的商品列表分页
         */
        fun page(requestBody: AppMyGoodsPageReqVo, callback: Callback) {
            val url = "$BASE_URL${preUrl}/page"
            OkHttpUtil.asyPost(
                url,
                GsonUtil.toJson(requestBody),
                Headers.headersOf(APP_TOKEN, UserInfoManager.getUser()!!.token),
                callback
            )
        }

        /**
         * 增加商品
         */
        fun insert(name: String, callback: Callback) {
            val url = "$BASE_URL${preUrl}"
            OkHttpUtil.asyPut(
                url,
                name,
                Headers.headersOf(APP_TOKEN, UserInfoManager.getUser()!!.token),
                callback
            )
        }

        /**
         * 删除商品
         */
        fun delete(ids: Array<Int>, callback: Callback) {
            val url = "$BASE_URL${preUrl}"
            OkHttpUtil.asyDelete(
                url,
                GsonUtil.toJson(ids),
                Headers.headersOf(APP_TOKEN, UserInfoManager.getUser()!!.token),
                callback
            )
        }

        /**
         * 修改我的商品
         */
        fun update(requestBody: AppMyGoodsUpdateReqVo, callback: Callback) {
            val url = "$BASE_URL${preUrl}/update"
            OkHttpUtil.asyPost(
                url,
                GsonUtil.toJson(requestBody),
                Headers.headersOf(APP_TOKEN, UserInfoManager.getUser()!!.token),
                callback
            )
        }
    }
}