package com.shenjies88.practice.kotlin_practice_backend.service

import com.shenjies88.practice.kotlin_practice_backend.manager.MyCacheManager
import com.shenjies88.practice.kotlin_practice_backend.mapper.AdminUserMapper
import com.shenjies88.practice.kotlin_practice_backend.vo.admin_user.req.AdminLoginReqVo
import com.shenjies88.practice.kotlin_practice_backend.vo.admin_user.resp.AdminLoginRespVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.util.*

/**
 * @author shenjies88
 * @since 2020/8/24-10:11 AM
 */
@Service
class AdminUserService @Autowired constructor(
        private val adminUserMapper: AdminUserMapper,
        private val myCacheManager: MyCacheManager) {

    fun login(param: AdminLoginReqVo): AdminLoginRespVo {
        //查询该账号是否存在，并且判断密码是否正确
        val user = adminUserMapper.getByAccount(param.account)
        Assert.isTrue(param.pwd == user!!.pwd, "账号密码不匹配")
        val token = UUID.randomUUID().toString()
        val result = AdminLoginRespVo(user, token)
        myCacheManager.setAdminToken(token, result)
        myCacheManager.setAdminLiveToken(user.id, token)
        return result
    }
}