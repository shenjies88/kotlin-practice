package com.shenjies88.practice.kotlin_practice_backend.vo.admin_user.req

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author shenjies88
 * @since 2020/8/23-5:09 PM
 */
@ApiModel("登陆请求")
data class AdminLoginReqVo(
        @ApiModelProperty("账号")
        val account: String?,
        @ApiModelProperty("密码")
        val pwd: String?)