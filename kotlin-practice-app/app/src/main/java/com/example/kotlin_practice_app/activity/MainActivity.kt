package com.example.kotlin_practice_app.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.kotlin_practice_app.R
import com.example.kotlin_practice_app.callback.BaseCallback
import com.example.kotlin_practice_app.client.BackendClient
import com.example.kotlin_practice_app.contant.AppConstant.APP_TOKEN
import com.example.kotlin_practice_app.contant.AppConstant.INTERNET_PERMISSION_CODE
import com.example.kotlin_practice_app.contant.ClientConstant.UN_AUTHORIZATION_CODE
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.manager.TokenManager
import com.example.kotlin_practice_app.utils.GsonUtil
import com.example.kotlin_practice_app.vo.AppLoginRespVo
import com.example.kotlin_practice_app.vo.HttpResultVo
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import com.google.gson.reflect.TypeToken
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.lang.ref.WeakReference


class MainActivity : AppCompatActivity() {

    //Handler
    private lateinit var toastHandler: ToastHandler
    private lateinit var userInfoHandler: MyInfoHandler

    //控件
    private lateinit var topAppBar: MaterialToolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var nvHeaderLayout: View
    private lateinit var nvHeaderIcon: ImageView
    lateinit var nvHeaderAccount: TextView
    lateinit var nvHeaderNickname: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //请求网络权限
        when (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)) {
            PackageManager.PERMISSION_GRANTED -> {

            }
            PackageManager.PERMISSION_DENIED -> {
                this.requestPermissions(
                    arrayOf(Manifest.permission.INTERNET),
                    INTERNET_PERMISSION_CODE
                )
            }
        }

        val sharedPreferences = getSharedPreferences(APP_TOKEN, Context.MODE_PRIVATE)
        val appToken = sharedPreferences.getString(APP_TOKEN, "")

        //如果没有token就启动LoginActivity
        if (appToken.isNullOrBlank()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "登陆已失效，请重新进行登陆", Toast.LENGTH_SHORT).show()
            return
        }

        toastHandler = ToastHandler(WeakReference(this))

        topAppBar = findViewById(R.id.top_app_bar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        nvHeaderLayout = navigationView.getHeaderView(0)
        nvHeaderIcon = nvHeaderLayout.findViewById(R.id.nv_header_icon)
        nvHeaderAccount = nvHeaderLayout.findViewById(R.id.nv_header_account)
        nvHeaderNickname = nvHeaderLayout.findViewById(R.id.nv_header_nickname)

        topAppBar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        //请求用户信息
        userInfoHandler = MyInfoHandler(WeakReference(this))
        BackendClient.myInfo(appToken, MyInfoCallBack(this, toastHandler, userInfoHandler))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.nv_menu, menu)
        return true
    }

    /**
     * 权限申请响应
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            INTERNET_PERMISSION_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) else {
                    Toast.makeText(this, "请允许使用网络", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * 更新用户信息
     */
    class MyInfoHandler(private val context: WeakReference<MainActivity>) : Handler() {
        override fun handleMessage(msg: Message) {
            val appLoginRespVo = msg.obj as AppLoginRespVo
            context.get()?.nvHeaderAccount?.text = appLoginRespVo.account
            context.get()?.nvHeaderNickname?.text = appLoginRespVo.nickname
        }
    }

    /**
     * 我的用户信息回调
     */
    class MyInfoCallBack(
        private val context: MainActivity,
        toastHandler: ToastHandler,
        private val myInfoHandler: MyInfoHandler
    ) : BaseCallback(toastHandler), Callback {

        override fun onFailure(call: Call, e: IOException) {
            Log.e("MyInfoCallBack-onFailure", e.stackTraceToString())
            sendToast("我的信息请求出现异常")
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
                    if (resultVo.code == UN_AUTHORIZATION_CODE) {
                        context.startActivity(Intent(context, LoginActivity::class.java))
                        context.finish()
                    }
                    return
                }
                val result = resultVo.data
                TokenManager.setToken(result!!.token)
                val msg = Message.obtain()
                msg.obj = result
                myInfoHandler.sendMessage(msg)
            } catch (e: Exception) {
                Log.e("MyInfoCallBack-onResponse", e.stackTraceToString())
                sendToast("我的信息请求出现异常")
            }
        }
    }
}