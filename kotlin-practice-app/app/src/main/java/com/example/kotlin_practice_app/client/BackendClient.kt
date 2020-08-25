package com.example.kotlin_practice_app.client

import android.util.Log
import com.example.kotlin_practice_app.activity.LoginActivity
import com.example.kotlin_practice_app.callback.LoginCallBack
import com.example.kotlin_practice_app.contant.ClientConstant.BASE_URL
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.utils.OkHttpUtil
import com.example.kotlin_practice_app.vo.AppLoginReqVo
import com.example.kotlin_practice_app.vo.AppLoginRespVo
import com.example.kotlin_practice_app.vo.HttpResultVo
import com.google.gson.reflect.TypeToken
import java.lang.Exception

class BackendClient {
    companion object {
        fun login(account: String, pwd: String, callback: LoginCallBack)  {
            val url = BASE_URL + "/app/authentication/login"
            val requestBody = AppLoginReqVo(account, pwd)
            OkHttpUtil.asyPost(url, GsonUtil.toJson(requestBody),callback)
        }
    }
}