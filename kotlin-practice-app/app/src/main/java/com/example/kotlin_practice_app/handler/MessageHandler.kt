package com.example.kotlin_practice_app.handler

import android.app.Activity
import android.os.Handler
import android.os.Message
import android.widget.Toast


class MessageHandler(private val context: Activity) : Handler() {

    override fun handleMessage(msg: Message) {
        Toast.makeText(context,msg.obj as String,Toast.LENGTH_SHORT).show()
    }
}