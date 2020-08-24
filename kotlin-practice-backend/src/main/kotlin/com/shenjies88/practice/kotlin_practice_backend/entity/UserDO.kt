package com.shenjies88.practice.kotlin_practice_backend.entity

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

/**
 * @author shenjies88
 * @since 2020/8/23-3:01 PM
 */
@ApiModel("用户实体")
data class UserDO(
        @ApiModelProperty("主键")
        var id: Int?,
        @ApiModelProperty("账号")
        var account: String?,
        @ApiModelProperty("密码")
        var pwd: String?,
        @ApiModelProperty("昵称")
        var nickname: String?,
        @ApiModelProperty("头像")
        var icon: String?) {
    constructor() : this(null, null, null, null, null)
}
