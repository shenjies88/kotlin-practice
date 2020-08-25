package com.example.kotlin_practice_app.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_practice_app.R
import com.example.kotlin_practice_app.callback.LoginCallBack
import com.example.kotlin_practice_app.client.BackendClient
import com.example.kotlin_practice_app.handler.MessageHandler
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private lateinit var btReg: Button
    private lateinit var btLogin: Button
    private lateinit var editAccount: TextInputLayout
    private lateinit var editPwd: TextInputLayout
    private lateinit var editNickname: TextInputLayout
    private lateinit var messageHandler : MessageHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        messageHandler = MessageHandler(this)
        btReg = findViewById(R.id.bt_reg)
        btLogin = findViewById(R.id.bt_login)
        editAccount = findViewById(R.id.edit_account)
        editPwd = findViewById(R.id.edit_pwd)
        editNickname = findViewById(R.id.edit_nickname)

        btReg.setOnClickListener {

        }

        btLogin.setOnClickListener {
            val account = editAccount.editText!!.text.toString()
            val pwd = editPwd.editText!!.text.toString()
            BackendClient.login(account, pwd, LoginCallBack(this,messageHandler))
        }
    }
}