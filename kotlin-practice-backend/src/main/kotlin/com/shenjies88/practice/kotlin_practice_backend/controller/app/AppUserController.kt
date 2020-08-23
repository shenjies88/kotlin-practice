package com.shenjies88.practice.kotlin_practice_backend.controller.app

import com.shenjies88.practice.kotlin_practice_backend.service.UserService
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo.Companion.successReturn
import com.shenjies88.practice.kotlin_practice_backend.vo.user.AppLoginReqVo
import com.shenjies88.practice.kotlin_practice_backend.vo.user.AppLoginRespVo
import com.shenjies88.practice.kotlin_practice_backend.vo.user.AppRegisteredReqVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.Assert
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author shenjies88
 * @since 2020/8/23-4:00 PM
 */
@Api(tags = ["用户接口"])
@RequestMapping("/app/user")
@RestController
class AppUserController @Autowired constructor(private val userService: UserService) {

    @ApiOperation("注册")
    @PostMapping("/registered")
    fun registered(@RequestBody param: AppRegisteredReqVo): HttpResultVo<*>? {
        Assert.hasText(param.account, "账号不能为空")
        Assert.hasText(param.pwd, "密码不能为空")
        Assert.hasText(param.nickname, "昵称不能为空")
        userService.registered(param)
        return successReturn()
    }

    @ApiOperation("登陆")
    @PostMapping("/login")
    fun login(@RequestBody param: AppLoginReqVo): HttpResultVo<AppLoginRespVo> {
        Assert.hasText(param.account, "账号不能为空")
        Assert.hasText(param.pwd, "密码不能为空")
        return successReturn(userService.login(param))
    }
}