package com.shenjies88.practice.kotlin_practice_backend.service

import com.shenjies88.practice.kotlin_practice_backend.entity.UserDO
import com.shenjies88.practice.kotlin_practice_backend.mapper.UserMapper
import com.shenjies88.practice.kotlin_practice_backend.vo.user.AppRegisteredReqVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert

/**
 * @author shenjies88
 * @since 2020/8/23-3:07 PM
 */
@Service
class UserService {

    private var userMapper: UserMapper

    @Autowired
    constructor(userMapper: UserMapper) {
        this.userMapper = userMapper
    }

    @Transactional(rollbackFor = [Exception::class])
    fun registered(param: AppRegisteredReqVo) {
        //查看该账号是否存在
        Assert.isNull(userMapper.getByAccount(param.account), "该账号已存在")
        //插入数据
        val userDO = UserDO(null, param.account, param.pwd, param.nickname, null)
        userMapper.insert(userDO)
    }
}