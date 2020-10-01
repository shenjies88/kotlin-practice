package com.example.kotlin_practice_app.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_practice_app.R
import com.example.kotlin_practice_app.callback.LoginCallBack
import com.example.kotlin_practice_app.callback.RegisteredCallBack
import com.example.kotlin_practice_app.client.BackendClient
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.vo.AppLoginReqVo
import com.example.kotlin_practice_app.vo.AppRegisteredReqVo
import com.google.android.material.textfield.TextInputLayout
import java.lang.ref.WeakReference

class LoginActivity : AppCompatActivity() {

    //Handler
    private lateinit var toastHandler: ToastHandler

    //控件
    private lateinit var btReg: Button
    private lateinit var btLogin: Button
    private lateinit var editAccount: TextInputLayout
    private lateinit var editPwd: TextInputLayout
    private lateinit var editNickname: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        toastHandler = ToastHandler(WeakReference(this))

        btReg = findViewById(R.id.bt_reg)
        btLogin = findViewById(R.id.bt_login)
        editAccount = findViewById(R.id.edit_account)
        editPwd = findViewById(R.id.edit_pwd)
        editNickname = findViewById(R.id.edit_nickname)

        btReg.setOnClickListener {
            val account = editAccount.editText!!.text.toString()
            val pwd = editPwd.editText!!.text.toString()
            val nickname = editNickname.editText!!.text.toString()
            BackendClient.AppAuthentication.registered(
                AppRegisteredReqVo(account, pwd, nickname),
                RegisteredCallBack(toastHandler)
            )
        }

        btLogin.setOnClickListener {
            val account = editAccount.editText!!.text.toString()
            val pwd = editPwd.editText!!.text.toString()
            BackendClient.AppAuthentication.login(
                AppLoginReqVo(account, pwd),
                LoginCallBack(this, toastHandler)
            )
        }
    }
}