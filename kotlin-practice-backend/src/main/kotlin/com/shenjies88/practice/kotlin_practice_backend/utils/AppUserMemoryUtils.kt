package com.shenjies88.practice.kotlin_practice_backend.utils

import com.shenjies88.practice.kotlin_practice_backend.vo.user.resp.AppLoginRespVo

/**
 * @author shenjies88
 * @since 2020/8/23-5:31 PM
 */
object AppUserMemoryUtils {

    private val userHold: ThreadLocal<AppLoginRespVo> = ThreadLocal()
    fun save(user: AppLoginRespVo) = userHold.set(user)
    fun get(): AppLoginRespVo = userHold.get()
}