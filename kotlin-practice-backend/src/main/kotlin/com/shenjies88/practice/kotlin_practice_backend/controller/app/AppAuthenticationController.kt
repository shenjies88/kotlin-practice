package com.shenjies88.practice.kotlin_practice_backend.controller.app

import com.shenjies88.practice.kotlin_practice_backend.service.UserService
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo.Companion.successReturn
import com.shenjies88.practice.kotlin_practice_backend.vo.user.req.AppLoginReqVo
import com.shenjies88.practice.kotlin_practice_backend.vo.user.req.AppRegisteredReqVo
import com.shenjies88.practice.kotlin_practice_backend.vo.user.resp.AppLoginRespVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * @author shenjies88
 * @since 2020/8/23-4:00 PM
 */
@Api(tags = ["鉴权接口"])
@RequestMapping("/app/authentication")
@RestController
class AppAuthenticationController @Autowired constructor(private val userService: UserService) {

    @ApiOperation("注册")
    @PostMapping("/registered")
    fun registered(@RequestBody @Valid param: AppRegisteredReqVo): HttpResultVo<Nothing> {
        userService.registered(param)
        return successReturn()
    }

    @ApiOperation("登陆")
    @PostMapping("/login")
    fun login(@RequestBody @Valid param: AppLoginReqVo): HttpResultVo<AppLoginRespVo> {
        return successReturn(userService.login(param))
    }
}