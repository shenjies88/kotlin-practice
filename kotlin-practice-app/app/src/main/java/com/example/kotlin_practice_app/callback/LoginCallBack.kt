package com.example.kotlin_practice_app.callback

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.util.Log
import com.example.kotlin_practice_app.activity.LoginActivity
import com.example.kotlin_practice_app.activity.MainActivity
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.manager.TokenManager
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.vo.AppLoginRespVo
import com.example.kotlin_practice_app.vo.HttpResultVo
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class LoginCallBack(private val context: LoginActivity, private val toastHandler: ToastHandler) :
    Callback {

    private fun sendToast(s: String?) {
        val msg = Message.obtain()
        msg.obj = s
        toastHandler.sendMessage(msg)
    }

    override fun onFailure(call: Call, e: IOException) {
        Log.e("LoginCallBack-onFailure", e.stackTraceToString())
        sendToast("登陆请求出现异常")
    }

    override fun onResponse(call: Call, response: Response) {
        val resultString = response.body!!.string()
        try {
            val resultVo = GsonUtil.fromJson<HttpResultVo<AppLoginRespVo>>(
                resultString,
                object : TypeToken<HttpResultVo<AppLoginRespVo>>() {}.type
            )
            if (!resultVo.success) {
                sendToast(resultVo.errorMsg)
                return
            }
            val result = resultVo.data
            //缓存token
            TokenManager.setToken(result!!.token)
            //传递数据启动MainActivity
            val intent = Intent(context, MainActivity::class.java)
            val data = Bundle()
            data.putInt("id", result.id!!)
            data.putString("account", result.account)
            data.putString("nickname", result.nickname)
            data.putString("icon", result.icon)
            data.putString("token", result.token)
            intent.putExtras(data)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e("LoginCallBack-onResponse", e.stackTraceToString())
            sendToast("登陆请求出现异常")
        }
    }
}