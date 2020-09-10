package com.shenjies88.practice.kotlin_practice_backend.vo.user.req

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank

/**
 * @author shenjies88
 * @since 2020/8/23-3:49 PM
 */
@ApiModel("注册请求")
data class AppRegisteredReqVo(
        @get: NotBlank(message = "账号不能为空")
        @ApiModelProperty("账号")
        val account: String?,
        @get: NotBlank(message = "密码不能为空")
        @ApiModelProperty("密码")
        val pwd: String?,
        @get: NotBlank(message = "昵称不能为空")
        @ApiModelProperty("昵称")
        val nickname: String?)