package com.example.kotlin_practice_app.callback

import android.content.Context
import android.content.Intent
import android.os.Message
import android.util.Log
import com.example.kotlin_practice_app.activity.LoginActivity
import com.example.kotlin_practice_app.activity.MainActivity
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.vo.AppLoginRespVo
import com.example.kotlin_practice_app.vo.HttpResultVo
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class MyInfoCallBack(
    private val context: Context,
    toastHandler: ToastHandler,
    private val myInfoHandler: MainActivity.MyInfoHandler
) :
    BaseCallback(toastHandler), Callback {

    override fun onFailure(call: Call, e: IOException) {
        Log.e("MyInfoCallBack-onFailure", e.stackTraceToString())
        sendToast("我的信息请求出现异常")
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
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
                return
            }
            val result = resultVo.data
            val msg = Message.obtain()
            msg.obj = result
            myInfoHandler.sendMessage(msg)
        } catch (e: Exception) {
            Log.e("MyInfoCallBack-onResponse", e.stackTraceToString())
            sendToast("我的信息请求出现异常")
        }
    }
}