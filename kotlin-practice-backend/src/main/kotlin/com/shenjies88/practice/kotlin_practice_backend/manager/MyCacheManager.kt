package com.shenjies88.practice.kotlin_practice_backend.manager

import com.shenjies88.practice.kotlin_practice_backend.vo.user.AppLoginRespVo
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author shenjies88
 * @since 2020/8/23-6:07 PM
 */
@Component
class MyCacheManager {

    private val appTokenMap: HashMap<String, AppLoginRespVo> = HashMap()
    private val appLiveTokenMap: HashMap<Int?, String> = HashMap()

    fun setAppToken(token: String, any: AppLoginRespVo) = appTokenMap.put(token, any)

    fun setAppLiveToken(id: Int?, token: String) = appLiveTokenMap.put(id, token)

    fun getByAppToken(token: String): AppLoginRespVo? = appTokenMap[token]

    fun getByAppIdToken(id: Int?): String? = appLiveTokenMap[id]

}