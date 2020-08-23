package com.shenjies88.practice.kotlin_practice_backend.vo.user.req

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author shenjies88
 * @since 2020/8/23-3:49 PM
 */
@ApiModel("注册请求")
data class AppRegisteredReqVo(
        @ApiModelProperty("账号")
        val account: String?,
        @ApiModelProperty("密码")
        val pwd: String?,
        @ApiModelProperty("昵称")
        val nickname: String?)