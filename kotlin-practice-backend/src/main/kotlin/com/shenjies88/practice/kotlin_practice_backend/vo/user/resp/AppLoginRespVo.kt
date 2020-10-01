package com.shenjies88.practice.kotlin_practice_backend.vo.user.resp

import com.shenjies88.practice.kotlin_practice_backend.entity.UserDO
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author shenjies88
 * @since 2020/8/23-5:09 PM
 */
@ApiModel("登陆返回")
data class AppLoginRespVo(private val user: UserDO, private val tk: String) {

    @ApiModelProperty("主键")
    val id: Int? = user.id

    @ApiModelProperty("账号")
    val account: String? = user.account

    @ApiModelProperty("昵称")
    val nickname: String? = user.nickname

    @ApiModelProperty("头像")
    val icon: String? = user.icon

    @ApiModelProperty("token")
    val token: String = tk
}

