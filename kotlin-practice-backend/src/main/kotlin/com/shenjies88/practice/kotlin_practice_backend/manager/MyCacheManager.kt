package com.shenjies88.practice.kotlin_practice_backend.manager

import com.shenjies88.practice.kotlin_practice_backend.vo.admin_user.resp.AdminLoginRespVo
import com.shenjies88.practice.kotlin_practice_backend.vo.user.resp.AppLoginRespVo
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author shenjies88
 * @since 2020/8/23-6:07 PM
 */
@Component
object MyCacheManager {

    private val appTokenMap: HashMap<String, AppLoginRespVo> = HashMap()
    private val appLiveTokenMap: HashMap<Int?, String> = HashMap()
    private val adminTokenMap: HashMap<String, AdminLoginRespVo> = HashMap()
    private val adminLiveTokenMap: HashMap<Int?, String> = HashMap()

    fun setAppToken(token: String, any: AppLoginRespVo) = appTokenMap.put(token, any)

    fun setAppLiveToken(id: Int?, token: String) = appLiveTokenMap.put(id, token)

    fun getByAppToken(token: String): AppLoginRespVo? = appTokenMap[token]

    fun getByAppIdToken(id: Int?): String? = appLiveTokenMap[id]

    fun setAdminToken(token: String, any: AdminLoginRespVo) = adminTokenMap.put(token, any)

    fun setAdminLiveToken(id: Int?, token: String) = adminLiveTokenMap.put(id, token)

    fun removeAdminLiveToken(id: Int?) = adminLiveTokenMap.remove(id)

    fun getByAdminToken(token: String): AdminLoginRespVo? = adminTokenMap[token]

    fun getByAdminIdToken(id: Int?): String? = adminLiveTokenMap[id]

}