package com.shenjies88.practice.kotlin_practice_backend.manager

import org.springframework.stereotype.Component
import java.util.*

/**
 * @author shenjies88
 * @since 2020/8/23-6:07 PM
 */
@Component
class MyCacheManager {

    private val map: HashMap<String, Any> = HashMap()

    fun setToken(token: String, any: Any) = map.put(token, any)

    fun getByToken(token: String): Any? = map[token]
}