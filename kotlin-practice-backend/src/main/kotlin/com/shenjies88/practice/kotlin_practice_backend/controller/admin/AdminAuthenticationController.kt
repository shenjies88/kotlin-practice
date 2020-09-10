package com.shenjies88.practice.kotlin_practice_backend.controller.admin

import com.shenjies88.practice.kotlin_practice_backend.service.AdminUserService
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo.Companion.successReturn
import com.shenjies88.practice.kotlin_practice_backend.vo.admin_user.req.AdminLoginReqVo
import com.shenjies88.practice.kotlin_practice_backend.vo.admin_user.resp.AdminLoginRespVo
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
@RequestMapping("/admin/authentication")
@RestController
class AdminAuthenticationController @Autowired constructor(private val adminUserService: AdminUserService) {

    @ApiOperation("登陆")
    @PostMapping("/login")
    fun login(@RequestBody @Valid param: AdminLoginReqVo): HttpResultVo<AdminLoginRespVo> {
        return successReturn(adminUserService.login(param))
    }
}