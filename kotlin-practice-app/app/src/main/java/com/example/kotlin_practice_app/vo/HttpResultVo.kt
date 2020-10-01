package com.example.kotlin_practice_app.vo

/**
 * Http请求返回
 */
class HttpResultVo<T>(
        val success: Boolean,
        val code: Int,
        val data: T?,
        val errorMsg: String?)