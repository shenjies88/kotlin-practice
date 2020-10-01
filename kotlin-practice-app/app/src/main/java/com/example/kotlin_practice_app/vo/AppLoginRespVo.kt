package com.example.kotlin_practice_app.vo

data class AppLoginRespVo(
    val id: Int?,
    val account: String?,
    val nickname: String?,
    val icon: String?,
    var token: String
)