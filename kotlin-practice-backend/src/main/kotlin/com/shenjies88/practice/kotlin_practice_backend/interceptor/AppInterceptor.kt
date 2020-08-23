package com.shenjies88.practice.kotlin_practice_backend.interceptor

import com.shenjies88.practice.kotlin_practice_backend.manager.MyCacheManager
import com.shenjies88.practice.kotlin_practice_backend.utils.AppUserMemoryUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.Assert
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author shenjies88
 * @since 2020/8/23-5:40 PM
 */
@Component
class AppInterceptor @Autowired constructor(private val myCacheManager: MyCacheManager) : HandlerInterceptor {

    private val APP_TOKEN: String = "app-token"

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader(APP_TOKEN)
        Assert.hasText(token, "请携带令牌")
        val user = myCacheManager.getByAppToken(token)
        Assert.notNull(user, "请先进行登陆")
        val liveToken = myCacheManager.getByAppIdToken(user!!.id)
        Assert.isTrue(liveToken != null && liveToken == token, "令牌已失效，请重新登陆")
        AppUserMemoryUtils.setAppUser(user)
        return true
    }
}