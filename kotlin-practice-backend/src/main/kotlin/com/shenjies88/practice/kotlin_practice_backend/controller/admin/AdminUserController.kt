package com.shenjies88.practice.kotlin_practice_backend.controller.admin

import com.shenjies88.practice.kotlin_practice_backend.entity.UserDO
import com.shenjies88.practice.kotlin_practice_backend.service.UserService
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo
import com.shenjies88.practice.kotlin_practice_backend.vo.HttpResultVo.Companion.successReturn
import com.shenjies88.practice.kotlin_practice_backend.vo.PageVo
import com.shenjies88.practice.kotlin_practice_backend.vo.user.req.AdminUserPageReqVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
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

    @ApiOperation("用户列表")
    @PostMapping("/page")
    fun page(@RequestBody param: AdminUserPageReqVo): HttpResultVo<PageVo<UserDO>> {
        return successReturn(userService.adminPage(param))
    }
}