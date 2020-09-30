package com.shenjies88.practice.kotlin_practice_backend.vo.admin_user.resp

import com.shenjies88.practice.kotlin_practice_backend.entity.AdminUserDO
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author shenjies88
 * @since 2020/8/23-10:49 PM
 */
@ApiModel("登陆返回")
class AdminLoginRespVo(user: AdminUserDO, tk: String) {

    @ApiModelProperty("主键")
    val id: Int? = user.id

    @ApiModelProperty("账号")
    val account: String? = user.account

    @ApiModelProperty("token")
    val token: String = tk
}