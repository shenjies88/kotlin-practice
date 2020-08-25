package com.example.kotlin_practice_app.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.kotlin_practice_app.R
import com.example.kotlin_practice_app.contant.AppConstant.INTERNET_PERMISSION_CODE
import com.example.kotlin_practice_app.manager.TokenManager
import com.google.android.material.appbar.MaterialToolbar


class MainActivity : AppCompatActivity() {

    private lateinit var topAppBar: MaterialToolbar
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //请求网络权限
        when (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) ){
            PackageManager.PERMISSION_GRANTED -> {

            }
            PackageManager.PERMISSION_DENIED -> {
                this.requestPermissions(
                    arrayOf(Manifest.permission.INTERNET),
                    INTERNET_PERMISSION_CODE
                )
            }
        }

        //如果没有token就启动LoginActivity
        if (TokenManager.getToken().isNullOrBlank()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "登陆已失效，请重新进行登陆", Toast.LENGTH_SHORT).show()
        }

        topAppBar = findViewById(R.id.top_app_bar)
        drawerLayout = findViewById(R.id.drawer_layout)

        topAppBar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

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
}