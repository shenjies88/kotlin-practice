package com.example.kotlin_practice_app.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.kotlin_practice_app.R
import com.example.kotlin_practice_app.callback.MyInfoCallBack
import com.example.kotlin_practice_app.client.BackendClient
import com.example.kotlin_practice_app.contant.AppConstant
import com.example.kotlin_practice_app.contant.AppConstant.GOODS_DIALOG_FRAGMENT
import com.example.kotlin_practice_app.contant.AppConstant.INTERNET_PERMISSION_CODE
import com.example.kotlin_practice_app.fragment.GoodsDialogFragment
import com.example.kotlin_practice_app.handler.ToastHandler
import com.example.kotlin_practice_app.manager.UserInfoManager
import com.example.kotlin_practice_app.vo.AppLoginRespVo
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import java.lang.ref.WeakReference


class MainActivity : AppCompatActivity() {

    //Handler
    private lateinit var toastHandler: ToastHandler
    private lateinit var userInfoHandler: MyInfoHandler

    //控件
    private lateinit var topAppBar: MaterialToolbar
    private lateinit var navigationView: NavigationView
    private lateinit var nvHeaderLayout: View
    private lateinit var nvHeaderIcon: ImageView
    private lateinit var nvHeaderAccount: TextView
    private lateinit var nvHeaderNickname: TextView
    private lateinit var floatingActionButton: FloatingActionButton


    //layout
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var fragmentLayout: FrameLayout

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

        toastHandler = ToastHandler(WeakReference(this))
        userInfoHandler = MyInfoHandler(WeakReference(this))

        topAppBar = findViewById(R.id.top_app_bar)
        navigationView = findViewById(R.id.navigation_view)
        nvHeaderLayout = navigationView.getHeaderView(0)
        nvHeaderIcon = nvHeaderLayout.findViewById(R.id.nv_header_icon)
        nvHeaderAccount = nvHeaderLayout.findViewById(R.id.nv_header_account)
        nvHeaderNickname = nvHeaderLayout.findViewById(R.id.nv_header_nickname)
        floatingActionButton = findViewById(R.id.floating_action_button)

        drawerLayout = findViewById(R.id.drawer_layout)
        fragmentLayout = findViewById(R.id.root_fragment_layout)


        topAppBar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        //TODO 根据Fragment的不同，弹出不同对话框
        //弹出新增对话框
        floatingActionButton.setOnClickListener {
            GoodsDialogFragment.newInstant(this, toastHandler, AppConstant.INSERT)
                .show(supportFragmentManager, GOODS_DIALOG_FRAGMENT)
        }
    }


    override fun onStart() {
        val sharedPreferences = getSharedPreferences(AppConstant.APP_TOKEN, Context.MODE_PRIVATE)
        val appToken = sharedPreferences.getString(AppConstant.APP_TOKEN, "")
        //如果没有token就启动LoginActivity
        if (appToken.isNullOrBlank()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "登陆已失效，请重新进行登陆", Toast.LENGTH_SHORT).show()
            return
        }
        //请求用户信息
        userInfoHandler = MyInfoHandler(WeakReference(this))
        BackendClient.AppUser.myInfo(appToken, MyInfoCallBack(this, toastHandler, userInfoHandler))
        super.onStart()
    }

    override fun onStop() {
        UserInfoManager.clean()
        super.onStop()
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
     * 我的用户信息处理
     */
    class MyInfoHandler(private val context: WeakReference<MainActivity>) : Handler() {
        override fun handleMessage(msg: Message) {
            val appLoginRespVo = msg.obj as AppLoginRespVo
            context.get()?.nvHeaderAccount?.text = appLoginRespVo.account
            context.get()?.nvHeaderNickname?.text = appLoginRespVo.nickname
        }
    }

}