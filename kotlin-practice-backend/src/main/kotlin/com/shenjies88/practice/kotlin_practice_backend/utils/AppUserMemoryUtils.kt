package com.shenjies88.practice.kotlin_practice_backend.utils

import com.shenjies88.practice.kotlin_practice_backend.vo.user.AppLoginRespVo

/**
 * @author shenjies88
 * @since 2020/8/23-5:31 PM
 */
class AppUserMemoryUtils {

    companion object {
        private val userHold: ThreadLocal<AppLoginRespVo> = ThreadLocal()
        fun setAppUser(user: AppLoginRespVo) = userHold.set(user)
        fun getAppUser(): AppLoginRespVo? = userHold.get()
    }
}