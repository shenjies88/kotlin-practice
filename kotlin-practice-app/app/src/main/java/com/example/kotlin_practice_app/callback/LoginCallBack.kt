package com.example.kotlin_practice_app.callback

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.kotlin_practice_app.activity.MainActivity
import com.example.kotlin_practice_app.contant.AppConstant.APP_TOKEN
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

class LoginCallBack(private val context: Context, toastHandler: ToastHandler) :
    BaseCallback(toastHandler), Callback {

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
            //存储token
            val sharedPreferences = context.getSharedPreferences(APP_TOKEN, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(APP_TOKEN, result.token)
            editor.apply()

            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e("LoginCallBack-onResponse", e.stackTraceToString())
            sendToast("登陆请求出现异常")
        }
    }
}