package com.example.kotlin_practice_app.callback

import android.util.Log
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.vo.HttpResultVo
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * 注册回调
 */
class RegisteredCallBack(toastHandler: ToastHandler) :
    BaseCallback(toastHandler), Callback {
    override fun onFailure(call: Call, e: IOException) {
        Log.e("RegisteredCallBack-onFailure", e.stackTraceToString())
        sendToast("注册请求出现异常")
    }

    override fun onResponse(call: Call, response: Response) {
        try {
            val resultString = response.body!!.string()
            Log.i("RegisteredCallBack-onResponse", resultString)
            val resultVo = GsonUtil.fromJson<HttpResultVo<Nothing>>(
                resultString,
                object : TypeToken<HttpResultVo<Nothing>>() {}.type
            )
            if (!resultVo.success) {
                sendToast(resultVo.errorMsg)
            } else {
                sendToast("注册成功")
            }
        } catch (e: Exception) {
            Log.e("RegisteredCallBack-onResponse", e.stackTraceToString())
            sendToast("注册请求出现异常")
        }
    }
}