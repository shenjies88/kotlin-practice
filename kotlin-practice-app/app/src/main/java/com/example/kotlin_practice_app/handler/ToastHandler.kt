package com.example.kotlin_practice_app.handler

import android.app.Activity
import android.os.Handler
import android.os.Message
import android.widget.Toast
import java.lang.ref.WeakReference


class ToastHandler(private val context: WeakReference<Activity>) : Handler() {

    override fun handleMessage(msg: Message) {
        Toast.makeText(context.get(), msg.obj as String, Toast.LENGTH_SHORT).show()
    }
}