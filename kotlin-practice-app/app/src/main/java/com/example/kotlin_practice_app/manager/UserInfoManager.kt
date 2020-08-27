package com.example.kotlin_practice_app.manager

import com.example.kotlin_practice_app.vo.AppLoginRespVo

object UserInfoManager {

        private val APP_USER = "app-user"
        private val userMap = HashMap<String, AppLoginRespVo?>()
        fun saveUser(user: AppLoginRespVo?) = userMap.put(APP_USER, user)
        fun getUser(): AppLoginRespVo? = userMap[APP_USER]
        fun clean() = userMap.clear()
}