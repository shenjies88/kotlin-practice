package com.shenjies88.practice.kotlin_practice_backend.service

import com.shenjies88.practice.kotlin_practice_backend.entity.UserDO
import com.shenjies88.practice.kotlin_practice_backend.manager.MyCacheManager
import com.shenjies88.practice.kotlin_practice_backend.mapper.UserMapper
import com.shenjies88.practice.kotlin_practice_backend.vo.PageVo
import com.shenjies88.practice.kotlin_practice_backend.vo.user.UserCount
import com.shenjies88.practice.kotlin_practice_backend.vo.user.req.AdminUserPageReqVo
import com.shenjies88.practice.kotlin_practice_backend.vo.user.req.AppLoginReqVo
import com.shenjies88.practice.kotlin_practice_backend.vo.user.req.AppRegisteredReqVo
import com.shenjies88.practice.kotlin_practice_backend.vo.user.resp.AppLoginRespVo
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert
import java.util.*

/**
 * @author shenjies88
 * @since 2020/8/23-3:07 PM
 */
@Service
class UserService @Autowired constructor(
        private val userMapper: UserMapper,
        private val myCacheManager: MyCacheManager) {

    @Transactional(rollbackFor = [Exception::class])
    fun registered(param: AppRegisteredReqVo) {
        //查看该账号是否存在
        Assert.isNull(userMapper.getByAccount(param.account), "该账号已存在")
        //插入数据
        val userDO = UserDO(null, param.account, param.pwd, param.nickname, null)
        userMapper.insert(userDO)
    }

    fun login(param: AppLoginReqVo): AppLoginRespVo {
        //查询该账号是否存在，并且判断密码是否正确
        val user = userMapper.getByAccount(param.account)
        Assert.isTrue(param.pwd == user!!.pwd, "账号密码不匹配")
        val token = UUID.randomUUID().toString()
        val result = AppLoginRespVo(user, token)
        myCacheManager.setAppToken(token, result)
        myCacheManager.setAppLiveToken(user.id, token)
        return result
    }

    /**----------Admin方法----------**/

    fun adminPage(param: AdminUserPageReqVo): PageVo<UserDO> {
        val userCount = UserCount()
        BeanUtils.copyProperties(param, userCount)
        val total = userMapper.count(userCount)
        val result = PageVo<UserDO>()
        if (total > 0) {
            result.list = userMapper.adminPage(param)
        } else {
            result.list = emptyArray()
        }
        result.total = total
        return result
    }
}