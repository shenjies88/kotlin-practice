package com.example.kotlin_practice_app.manager

class TokenManager {

    companion object {
        private val APP_TOKEN = "app-token"
        private val tokenMap = HashMap<String, String>()
        fun setToken(token: String) = tokenMap.put(APP_TOKEN, token)
        fun getToken(): String? = tokenMap[APP_TOKEN]
    }
}