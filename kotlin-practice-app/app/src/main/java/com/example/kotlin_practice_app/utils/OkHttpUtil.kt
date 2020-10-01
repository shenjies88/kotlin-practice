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
        Log.i("OkHttpUtil-post-url", "url: $url")
        Log.i("OkHttpUtil-post-body", "body: $json")
        Log.i("OkHttpUtil-post-headers", "headers: ${GsonUtil.toJson(headers)}")
        val request: Request = Request.Builder()
            .url(url)
            .post(json.toRequestBody(JSON))
            .headers(headers)
            .build()
        client.newCall(request).enqueue(callback)
    }

    fun asyPut(url: String, json: String, headers: Headers, callback: Callback) {
        Log.i("OkHttpUtil-put-url", "url: $url")
        Log.i("OkHttpUtil-put-body", "body: $json")
        Log.i("OkHttpUtil-put-headers", "headers: ${GsonUtil.toJson(headers)}")
        val request: Request = Request.Builder()
            .url(url)
            .put(json.toRequestBody(JSON))
            .headers(headers)
            .build()
        client.newCall(request).enqueue(callback)
    }

    fun asyDelete(url: String, json: String, headers: Headers, callback: Callback) {
        Log.i("OkHttpUtil-ic_delete-url", "url: $url")
        Log.i("OkHttpUtil-ic_delete-body", "body: $json")
        Log.i("OkHttpUtil-ic_delete-headers", "headers: ${GsonUtil.toJson(headers)}")
        val request: Request = Request.Builder()
            .url(url)
            .delete(json.toRequestBody(JSON))
            .headers(headers)
            .build()
        client.newCall(request).enqueue(callback)
    }
}