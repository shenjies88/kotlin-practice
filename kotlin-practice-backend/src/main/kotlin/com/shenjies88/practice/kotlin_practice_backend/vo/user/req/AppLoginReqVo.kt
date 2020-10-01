package com.shenjies88.practice.kotlin_practice_backend.vo.user.req

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank

/**
 * @author shenjies88
 * @since 2020/8/23-5:09 PM
 */
@ApiModel("登陆请求")
data class AppLoginReqVo(
        @get: NotBlank(message = "账号不能为空")
        @ApiModelProperty("账号")
        val account: String?,
        @get: NotBlank(message = "密码不能为空")
        @ApiModelProperty("密码")
        val pwd: String?)