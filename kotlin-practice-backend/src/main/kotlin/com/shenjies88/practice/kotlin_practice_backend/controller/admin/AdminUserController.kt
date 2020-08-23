package com.shenjies88.practice.kotlin_practice_backend.controller.admin

import com.shenjies88.practice.kotlin_practice_backend.service.UserService
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo.Companion.successReturn
import com.shenjies88.practice.kotlin_practice_backend.vo.admin_user.req.AdminLoginReqVo
import com.shenjies88.practice.kotlin_practice_backend.vo.admin_user.resp.AdminLoginRespVo
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
@RequestMapping("/admin/user")
@RestController
class AdminUserController @Autowired constructor(private val userService: UserService) {

    @ApiOperation("登陆")
    @PostMapping("/login")
    fun login(@RequestBody param: AdminLoginReqVo): HttpResultVo<AdminLoginRespVo> {
        Assert.hasText(param.account, "账号不能为空")
        Assert.hasText(param.pwd, "密码不能为空")
        return successReturn(userService.adminLogin(param))
    }
}