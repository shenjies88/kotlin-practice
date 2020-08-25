package com.example.kotlin_practice_app.callback

import android.os.Message
import com.example.kotlin_practice_app.handler.ToastHandler

open class BaseCallback(private val toastHandler: ToastHandler) {

    fun sendToast(s: String?) {
        val msg = Message.obtain()
        msg.obj = s
        toastHandler.sendMessage(msg)
    }
}