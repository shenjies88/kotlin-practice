package com.shenjies88.practice.kotlin_practice_backend.interceptor

import com.shenjies88.practice.kotlin_practice_backend.manager.MyCacheManager
import com.shenjies88.practice.kotlin_practice_backend.utils.AppUserMemoryUtils
import com.shenjies88.practice.kotlin_practice_backend.vo.user.AppLoginRespVo
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
        //如果该token在内存中存在，则设置对象进入上下文
        val token = request.getHeader(APP_TOKEN)
        val user = myCacheManager.getByToken(token)
        Assert.notNull(user, "请先进行登陆")
        AppUserMemoryUtils.setAppUser(user as AppLoginRespVo)
        return true
    }
}