package com.example.kotlin_practice_app.callback

import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.kotlin_practice_app.activity.LoginActivity
import com.example.kotlin_practice_app.activity.MainActivity
import com.example.kotlin_practice_app.contant.AppConstant
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.manager.UserInfoManager
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.vo.AppLoginRespVo
import com.example.kotlin_practice_app.vo.HttpResultVo
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * 登陆回调
 */
class LoginCallBack(private val context: LoginActivity, toastHandler: ToastHandler) :
    BaseCallback(toastHandler), Callback {

    override fun onFailure(call: Call, e: IOException) {
        Log.e("LoginCallBack-onFailure", e.stackTraceToString())
        sendToast("登陆请求出现异常")
    }

    override fun onResponse(call: Call, response: Response) {
        try {
            val resultString = response.body!!.string()
            Log.i("LoginCallBack-onResponse", resultString)
            val resultVo = GsonUtil.fromJson<HttpResultVo<AppLoginRespVo>>(
                resultString,
                object : TypeToken<HttpResultVo<AppLoginRespVo>>() {}.type
            )
            if (!authentication(resultVo, context)) {
                return
            }
            sendToast("登陆成功")
            val data = resultVo.data
            //传递数据启动MainActivity
            val intent = Intent(context, MainActivity::class.java)
            //存储token
            val sharedPreferences =
                context.getSharedPreferences(AppConstant.APP_TOKEN, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString(AppConstant.APP_TOKEN, data!!.token)
            editor.apply()
            UserInfoManager.saveUser(data)
            context.startActivity(intent)
            context.finish()
        } catch (e: Exception) {
            Log.e("LoginCallBack-onResponse", e.stackTraceToString())
            sendToast("登陆请求出现异常")
        }
    }
}