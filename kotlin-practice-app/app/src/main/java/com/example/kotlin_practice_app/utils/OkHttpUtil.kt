package com.example.kotlin_practice_app.utils

import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody


class OkHttpUtil {

    companion object {
        private val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
        private val client: OkHttpClient = OkHttpClient()

        fun asyPost(url: String, json: String, callback: Callback) {
            asyPost(url, json, Headers.headersOf(), callback)
        }

        fun asyPost(url: String, headers: Headers, callback: Callback) {
            asyPost(url, "", headers, callback)
        }

        fun asyPost(url: String, json: String, headers: Headers, callback: Callback) {
            val body = json.toRequestBody(JSON)
            Log.i("OkHttpUtil-asyPost", "url: $url")
            Log.i("OkHttpUtil-asyPost", "body: ${GsonUtil.toJson(json)}")
            Log.i("OkHttpUtil-asyPost", "headers: ${GsonUtil.toJson(headers)}")
            val request: Request = Request.Builder()
                .url(url)
                .post(body)
                .headers(headers)
                .build()
            client.newCall(request).enqueue(callback)
        }
    }
}