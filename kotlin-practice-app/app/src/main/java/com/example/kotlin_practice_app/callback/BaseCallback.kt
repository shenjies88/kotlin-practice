package com.example.kotlin_practice_app.callback

import android.app.Activity
import android.content.Intent
import android.os.Message
import com.example.kotlin_practice_app.activity.LoginActivity
import com.example.kotlin_practice_app.contant.ClientConstant
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.vo.HttpResultVo

open class BaseCallback(private val toastHandler: ToastHandler) {

    fun sendToast(s: String?) {
        val msg = Message.obtain()
        msg.obj = s
        toastHandler.sendMessage(msg)
    }

    fun authentication(resultVo: HttpResultVo<*>, context: Activity): Boolean {
        if (!resultVo.success) {
            sendToast(resultVo.errorMsg)
            if (resultVo.code == ClientConstant.UN_AUTHORIZATION_CODE) {
                context.startActivity(Intent(context, LoginActivity::class.java))
                context.finish()
            }
            return false
        }
        return true
    }
}