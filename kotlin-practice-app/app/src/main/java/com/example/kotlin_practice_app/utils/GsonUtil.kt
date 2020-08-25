package com.example.kotlin_practice_app.utils

import com.google.gson.Gson
import java.lang.reflect.Type

class GsonUtil {

    companion object {
        val gson = Gson()
        fun <T> toJson (data: T) : String = gson.toJson(data)

        fun <T> fromJson (json: String, type: Type) : T = gson.fromJson(json,type)
    }
}