package com.example.kotlin_practice_app.utils

import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody


object OkHttpUtil {

    private val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
    private val client: OkHttpClient = OkHttpClient()

    fun asyPost(url: String, json: String, callback: Callback) {
        asyPost(url, json, Headers.headersOf(), callback)
    }

    fun asyPost(url: String, headers: Headers, callback: Callback) {
        asyPost(url, "", headers, callback)
    }

    fun asyPost(url: String, json: String, headers: Headers, callback: Callback) {
        Log.i("OkHttpUtil-url", "url: $url")
        Log.i("OkHttpUtil-body", "body: $json")
        Log.i("OkHttpUtil-headers", "headers: ${GsonUtil.toJson(headers)}")
        val request: Request = Request.Builder()
            .url(url)
            .post(json.toRequestBody(JSON))
            .headers(headers)
            .build()
        client.newCall(request).enqueue(callback)
    }
}