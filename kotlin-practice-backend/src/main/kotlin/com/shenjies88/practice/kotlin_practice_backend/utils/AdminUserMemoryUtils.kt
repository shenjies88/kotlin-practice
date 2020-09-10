package com.shenjies88.practice.kotlin_practice_backend.utils

import com.shenjies88.practice.kotlin_practice_backend.vo.admin_user.resp.AdminLoginRespVo

/**
 * @author shenjies88
 * @since 2020/8/23-5:31 PM
 */
object AdminUserMemoryUtils {

    private val userHold: ThreadLocal<AdminLoginRespVo> = ThreadLocal()
    fun setAdminUser(user: AdminLoginRespVo) = userHold.set(user)
    fun getAdminUser(): AdminLoginRespVo = userHold.get()
}