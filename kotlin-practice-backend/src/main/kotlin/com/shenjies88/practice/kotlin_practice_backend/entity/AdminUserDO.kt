package com.shenjies88.practice.kotlin_practice_backend.entity

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author shenjies88
 * @since 2020/8/23-10:55 PM
 */
@ApiModel("管理员实体")
data class AdminUserDO(
        @ApiModelProperty("主键")
        var id: Int?,
        @ApiModelProperty("账号")
        var account: String?,
        @ApiModelProperty("密码")
        var pwd: String?) {
    constructor() : this(null, null, null)
}