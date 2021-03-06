package com.shenjies88.practice.kotlin_practice_backend.interceptor

import com.shenjies88.practice.kotlin_practice_backend.exception.MyAuthorizationException
import com.shenjies88.practice.kotlin_practice_backend.manager.MyCacheManager
import com.shenjies88.practice.kotlin_practice_backend.utils.AppUserMemoryUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
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
        if (!StringUtils.hasText(token)) {
            throw MyAuthorizationException("请携带令牌")
        }
        val user = myCacheManager.getByAppToken(token) ?: throw MyAuthorizationException("请先进行登陆")
        val liveToken = myCacheManager.getByAppIdToken(user.id)
        if (liveToken == null || liveToken != token) {
            throw MyAuthorizationException("令牌已失效，请重新登陆")
        }
        AppUserMemoryUtils.save(user)
        return true
    }
}