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
import com.example.kotlin_practice_app.vo.AppLoginReqVo
import com.example.kotlin_practice_app.vo.AppLoginRespVo
import com.example.kotlin_practice_app.vo.AppRegisteredReqVo
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
                //缓存token
                TokenManager.setToken(data!!.token)
                //传递数据启动MainActivity
                val intent = Intent(context, MainActivity::class.java)
                //存储token
                val sharedPreferences =
                    context.getSharedPreferences(AppConstant.APP_TOKEN, Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString(AppConstant.APP_TOKEN, data.token)
                editor.apply()
                TokenManager.setToken(data.token)
                context.startActivity(intent)
                context.finish()
            } catch (e: Exception) {
                Log.e("LoginCallBack-onResponse", e.stackTraceToString())
                sendToast("登陆请求出现异常")
            }
        }
    }
}