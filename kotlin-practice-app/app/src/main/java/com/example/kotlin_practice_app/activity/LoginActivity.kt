package com.example.kotlin_practice_app.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_practice_app.R
import com.example.kotlin_practice_app.callback.BaseCallback
import com.example.kotlin_practice_app.client.BackendClient
import com.example.kotlin_practice_app.contant.AppConstant
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.manager.TokenManager
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.vo.AppLoginRespVo
import com.example.kotlin_practice_app.vo.HttpResultVo
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
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

        }

        btLogin.setOnClickListener {
            val account = editAccount.editText!!.text.toString()
            val pwd = editPwd.editText!!.text.toString()
            BackendClient.login(account, pwd, LoginCallBack(this, toastHandler))
        }
    }

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
                val sharedPreferences = context.getSharedPreferences(AppConstant.APP_TOKEN, Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString(AppConstant.APP_TOKEN, result.token)
                editor.apply()

                context.startActivity(intent)
            } catch (e: Exception) {
                Log.e("LoginCallBack-onResponse", e.stackTraceToString())
                sendToast("登陆请求出现异常")
            }
        }
    }
}