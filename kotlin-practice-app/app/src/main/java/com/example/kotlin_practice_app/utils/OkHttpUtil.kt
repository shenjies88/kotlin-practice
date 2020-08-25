package com.example.kotlin_practice_app.utils

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody.Part.Companion.create


    class OkHttpUtil {

    companion object {
        private val JSON: MediaType = "application/json; charset=utf-8".toMediaType()
        private val client: OkHttpClient = OkHttpClient()

        fun asyPost(url: String, json: String,callback : Callback) {
            val body = RequestBody.create(JSON, json)
            val request: Request = Request.Builder()
                .url(url)
                .post(body)
                .build()
            client.newCall(request).enqueue(callback)
        }
    }
}