package com.shenjies88.practice.kotlin_practice_backend.interceptor

import com.shenjies88.practice.kotlin_practice_backend.manager.MyCacheManager
import com.shenjies88.practice.kotlin_practice_backend.utils.AdminUserMemoryUtils
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
class AdminInterceptor @Autowired constructor(private val myCacheManager: MyCacheManager) : HandlerInterceptor {

    private val ADMIN_TOKEN: String = "admin-token"

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val token = request.getHeader(ADMIN_TOKEN)
        val user = myCacheManager.getByAdminToken(token)
        Assert.notNull(user, "请先进行登陆")
        val liveToken = myCacheManager.getByAdminIdToken(user!!.id)
        Assert.isTrue(liveToken != null && liveToken == token, "令牌已失效，请重新登陆")
        AdminUserMemoryUtils.setAdminUser(user)
        return true
    }
}